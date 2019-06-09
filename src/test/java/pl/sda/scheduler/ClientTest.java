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
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.scheduler.clients.Client;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@WithMockUser
class ClientTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private String newClientInformationJson1 = "{\"name\":\"John\",\"surname\":\"Doe\",\"phoneNumber\":123456789,\"email\":\"jakis@email.com\"}";
    private String newClientInformationJson2 = "{\"name\":\"Mike\",\"surname\":\"Bar\",\"phoneNumber\":234567890,\"email\":\"jakis@email.pl\"}";
    private String newClientInformationJson3 = "{\"name\":\"Stan\",\"surname\":\"Ley\",\"phoneNumber\":345678901,\"email\":\"jakis@email.bom\"}";

    private Long addNewClient(String client) throws Exception {
        String createdClient = mockMvc.perform(post("/api/clients")
                .content(client).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();
        return objectMapper.readValue(createdClient, Client.class).getId();
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
        addNewClient(newClientInformationJson1);
        addNewClient(newClientInformationJson2);
        addNewClient(newClientInformationJson3);
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
        addNewClient(newClientInformationJson1);
        //WHEN
        //THAN
        mockMvc.perform(get("/api/clients"))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[0].surname", is("Doe")))
                .andExpect(jsonPath("$[0].phoneNumber", is(123456789)))
                .andExpect(jsonPath("$[0].email", is("jakis@email.com")))
                .andExpect(status().isOk());
    }

    @DisplayName("After Sending GET to /api/clients/{id} get client by id")
    @Test
    void test3() throws Exception {
        //GIVEN
        addNewClient(newClientInformationJson1);
        long id2 = addNewClient(newClientInformationJson2);
        long id3 = addNewClient(newClientInformationJson3);
        //WHEN
        mockMvc.perform(get("/api/clients/{id}", id3))
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
        addNewClient(newClientInformationJson1);
        addNewClient(newClientInformationJson2);
        addNewClient(newClientInformationJson3);
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
        long id = addNewClient(newClientInformationJson1);
        //WHEN
        mockMvc.perform(delete("/api/clients/{id}", id))
                .andExpect(status().isOk());
        //THAN
        mockMvc.perform(get("/api/clients"))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }

    @DisplayName("After sending put change all client data")
    @Test
    void test6() throws Exception {
        //GIVEN
        long id = addNewClient(newClientInformationJson1);
        String update = "{\"name\":\"Alexander\",\"surname\":\"TheBig\",\"phoneNumber\":1000,\"email\":\"alex@ande.rrr\"}";
        //WHEN
        mockMvc.perform(put("/api/clients/{id}", id)
                .content(update)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //THAN
        mockMvc.perform(get("/api/clients/{id}", id))
                .andExpect(jsonPath("$.name", is("Alexander")))
                .andExpect(jsonPath("$.surname", is("TheBig")))
                .andExpect(status().isOk());
    }

    void addNewAppointment(String appointment) throws Exception {
        mockMvc.perform(post("/api/appointments")
                .content(appointment)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();
    }

    @DisplayName("After deleting Client delate all apointments")
    @Test
    void test7() throws Exception {
        //GIVEN
        long id = addNewClient(newClientInformationJson1);
        String appointment1 = "{\"chosenDay\":\"2019-05-23\",\"chosenHour\":\"10:00\",\"clientId\":1}";
        String appointment2 = "{\"chosenDay\":\"2019-05-24\",\"chosenHour\":\"10:00\",\"clientId\":1}";
        String appointment3 = "{\"chosenDay\":\"2019-05-25\",\"chosenHour\":\"10:00\",\"clientId\":1}";
        addNewAppointment(appointment1);
        addNewAppointment(appointment2);
        addNewAppointment(appointment3);
        //WHEN
        mockMvc.perform(delete("/api/clients/{id}", id))
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
        long id = addNewClient(clientInfoJson);
        //WHEN
        mockMvc.perform(get("/api/clients/{id}", id))
                //THAN
                .andExpect(jsonPath("$.dateOfBirth", is("2018-01-01")))
                .andExpect(status().isOk());
    }
}
