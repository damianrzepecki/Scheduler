package pl.sda.scheduler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ClientsTest {
    @Autowired
    private MockMvc mockMvc;
    private String newClientInformationJson1 = "{\"name\":\"John\",\"surname\":\"Doe\",\"phoneNumber\":123456789,\"email\":\"jakis@email.com\"}";
    private String newClientInformationJson2 = "{\"name\":\"Mike\",\"surname\":\"Bar\",\"phoneNumber\":234567890,\"email\":\"jakis@email.pl\"}";
    private String newClientInformationJson3 = "{\"name\":\"Stan\",\"surname\":\"Ley\",\"phoneNumber\":345678901,\"email\":\"jakis@email.bom\"}";

    private void addNewClient(String client) throws Exception {
        mockMvc.perform(post("/api/clients")
                .content(client).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
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
    @DisplayName("After Sending GET to /api/clients list get sum of Clients = 3")
    @Test
    void test1_1() throws Exception {
        //GIVEN
        addNewClient(newClientInformationJson1);
        addNewClient(newClientInformationJson2);
        addNewClient(newClientInformationJson3);
        //WHEN
        mockMvc.perform(get("/api/clients"))
                //THAN
                .andExpect(jsonPath("$",hasSize(3)))
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
        addNewClient(newClientInformationJson2);
        addNewClient(newClientInformationJson3);
        //WHEN
        mockMvc.perform(get("/api/clients/3"))
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Stan")))
                .andExpect(jsonPath("$.phoneNumber", is(345678901)))
                .andExpect(jsonPath("$.email", is("jakis@email.bom")))
                .andExpect(status().isOk());
        //THAN
        mockMvc.perform(get("/api/clients"))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("John", "Mike", "Stan")))
                .andExpect(status().isOk());
    }
//    TODO Add message if no such client exists

    @DisplayName("Send get to /api/clinets/{surname} to find client by surname(?)")
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

    @DisplayName("Send delete /api/clients/{1} tp Delete Client By id")
    @Test
    void test5() throws Exception {
        //GIVEN
        addNewClient(newClientInformationJson1);
        //WHEN
        mockMvc.perform(delete("/api/clients/1"))
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
        addNewClient(newClientInformationJson1);
        String update = "{\"name\":\"Alexander\",\"surname\":\"TheBig\",\"phoneNumber\":1000,\"email\":\"alex@ande.rrr\"}";
        //WHEN
        mockMvc.perform(put("/api/clients/1")
                .content(update)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //THAN
        mockMvc.perform(get("/api/clients/1"))
                .andExpect(jsonPath("$.name", is("Alexander")))
                .andExpect(jsonPath("$.surname", is("TheBig")))
                .andExpect(status().isOk());
    }
}