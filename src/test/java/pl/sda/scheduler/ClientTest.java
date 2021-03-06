package pl.sda.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class ClientTest {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;

    private void addNewClient(String client) throws Exception {
        mockMvc.perform(post("/api/clients")
                .content(client).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();
    }

    private void addThreeClients() throws Exception {
        String newClientInformationJson1 = "{\"name\":\"John\",\"surname\":\"Doe\",\"dateOfBirth\":\"2000-02-02\",\"phoneNumber\":\"123456789\",\"email\":\"jakis@email.com\"}";
        addNewClient(newClientInformationJson1);
        String newClientInformationJson2 = "{\"name\":\"Mike\",\"surname\":\"Bar\",\"dateOfBirth\":\"2000-03-02\",\"phoneNumber\":\"234567890\",\"email\":\"jakis@email.pl\"}";
        addNewClient(newClientInformationJson2);
        String newClientInformationJson3 = "{\"name\":\"Stan\",\"surname\":\"Ley\",\"dateOfBirth\":\"2000-04-02\",\"phoneNumber\":\"345678901\",\"email\":\"jakis@email.bom\"}";
        addNewClient(newClientInformationJson3);
    }
    private void addNewAppointment(String appointment) throws Exception {
        mockMvc.perform(post("/api/appointments")
                .content(appointment)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    private void addAppointmentsToClientID_1() throws Exception {
        String appointment1 = "{\"chosenDay\":\"2019-05-23\",\"nameOfTreatment\":\"Zabieg1\",\"chosenHour\":\"10:00\",\"hourFinished\":\"11:00\",\"price\":\"123\",\"clientId\":1}";
        addNewAppointment(appointment1);
        String appointment2 = "{\"chosenDay\":\"2019-05-24\",\"nameOfTreatment\":\"Zabieg2\",\"chosenHour\":\"10:00\",\"hourFinished\":\"11:00\",\"price\":\"123\",\"clientId\":1}";
        addNewAppointment(appointment2);
        String appointment3 = "{\"chosenDay\":\"2019-05-25\",\"nameOfTreatment\":\"Zabieg3\",\"chosenHour\":\"10:00\",\"hourFinished\":\"11:00\",\"price\":\"123\",\"clientId\":1}";
        addNewAppointment(appointment3);
    }

    @DisplayName("After Sending GET to /api/clients list get OK status")
    @Test
    void test1() throws Exception {
        //GIVEN
        //WHEN
        mockMvc.perform(get("/api/clients"))
                //THAN
                .andExpect(status().isOk());
    }

    @DisplayName("After Sending GET to /api/clients list get sum of Client = 3")
    @Test
    void test1_1() throws Exception {
        //GIVEN
        addThreeClients();
        //WHEN
        mockMvc.perform(get("/api/clients"))
                //THAN
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(status().isOk());
    }

    @DisplayName("After Sending POST to /api/clients add a new Client")
    @Test
    void test2() throws Exception {
        //GIVEN
        addThreeClients();
        //WHEN
        //THAN
        mockMvc.perform(get("/api/clients"))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[0].surname", is("Doe")))
                .andExpect(jsonPath("$[0].dateOfBirth", is("2000-02-02")))
                .andExpect(jsonPath("$[0].phoneNumber", is(123456789)))
                .andExpect(jsonPath("$[0].email", is("jakis@email.com")))
                .andExpect(status().isOk());
    }

    @DisplayName("After Sending GET to /api/clients/{id} get client by id")
    @Test
    void test3() throws Exception {
        //GIVEN
        addThreeClients();
        //WHEN
        mockMvc.perform(get("/api/clients/{id}", 3))
                //THAN
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Stan")))
                .andExpect(jsonPath("$.phoneNumber", is(345678901)))
                .andExpect(jsonPath("$.email", is("jakis@email.bom")))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/clients"))
                //THAN
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("John", "Mike", "Stan")))
                .andExpect(status().isOk());
    }
    //    TODO Add message if no such client exists

    @DisplayName("Send get to /api/clients/  to find client by surname(?)")
    @Test
    void test4() throws Exception {
        //GIVEN
        addThreeClients();
        //WHEN
        //THAN
        mockMvc.perform(get("/api/clients/").param("surname", "Ley").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Stan")))
                .andExpect(status().isOk());
    }

    @DisplayName("Send delete /api/clients/{id} tp Delete Client By id")
    @Test
    void test5() throws Exception {
        //GIVEN
        addThreeClients();
        //WHEN
        mockMvc.perform(delete("/api/clients/{id}", 1))
                .andExpect(status().isOk());
        //THAN
        mockMvc.perform(get("/api/clients"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(status().isOk());
    }

    @DisplayName("After sending put change all client data")
    @Test
    void test6() throws Exception {
        //GIVEN
        addThreeClients();

        String update = "{\"id\":\"1\",\"name\":\"Alexander\",\"surname\":\"TheBig\",\"dateOfBirth\":\"2222-04-02\",\"phoneNumber\":\"100000000\",\"email\":\"alex@ande.rrr\"}";
        //WHEN
        mockMvc.perform(put("/api/clients/{id}", 1)
                .content(update)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //THAN
        mockMvc.perform(get("/api/clients/{id}", 1))
                .andExpect(jsonPath("$.name", is("Alexander")))
                .andExpect(jsonPath("$.surname", is("TheBig")))
                .andExpect(status().isOk());
    }


    @DisplayName("After deleting Client delete all appointments")
    @Test
    void test7() throws Exception {
        //GIVEN
        addThreeClients();
        addAppointmentsToClientID_1();
        //WHEN
        mockMvc.perform(delete("/api/clients/{id}", 1))
                .andExpect(status().isOk());
        //THAN
        mockMvc.perform(get("/api/appointments"))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @DisplayName("Check if newly added LocalDate = Date of Birth works as intended")
    @Test
    void test8() throws Exception {
        //GIVEN
        String clientInfoJson = "{\"name\":\"John\",\"surname\":\"Doe\"," +
                "\"dateOfBirth\":\"2018-01-01\",\"phoneNumber\":123456789," +
                "\"email\":\"jakis@email.com\"}";
        addNewClient(clientInfoJson);
        long id = 1;
        //WHEN
        mockMvc.perform(get("/api/clients/{id}", id))
                //THAN
                .andExpect(jsonPath("$.dateOfBirth", is("2018-01-01")))
                .andExpect(status().isOk());
    }
}
