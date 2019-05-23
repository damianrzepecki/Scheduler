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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AppointmentsTest {
    @Autowired
    private MockMvc mockMvc;
    private String newClientInformationJson1 = "{\"name\":\"John\",\"surname\":\"Doe\",\"phoneNumber\":123456789,\"email\":\"jakis@email.com\"}";
    private String appointmentJSON = "{\"chosenDay\":\"2019-05-23\",\"chosenHour\":\"10:00\",\"clientId\":1}";

    private void addNewClient(String client) throws Exception {
        mockMvc.perform(post("/api/clients")
                .content(client).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private void addNewAppointment(String appointment) throws Exception {
        mockMvc.perform(post("/api/appointments")
                .content(appointment)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Test if GET is giving status OK")
    @Test
    void test1() throws Exception {
        mockMvc.perform(get("/api/appointments")).andExpect(status().isOk());
    }

    @DisplayName("Add new Appointment to some day with user")
    @Test
    void test2() throws Exception {
        //GIVEN
        //String appointmentJSON
        //WHEN
        addNewClient(newClientInformationJson1);
        addNewAppointment(appointmentJSON);
        //THAN
        mockMvc.perform(get("/api/appointments"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].chosenDay", is("2019-05-23")))
                .andExpect(jsonPath("$[0].chosenHour", is("10:00")))
                .andExpect(jsonPath("$[0].clientId", is(1)))
                .andExpect(status().isOk());
    }

    @DisplayName("Test if ID is not taken if userID is empty or wrong(no such id exists")
    @Test
    void test3() throws Exception {
        //GIVEN
        //No user will be posted
        String appointmentJSONwithWrongID = "{\"chosenDay\":\"2019-05-23\",\"chosenHour\":\"10:00\",\"clientId\":2}";
        String appointmentJSONwithNoID = "{\"chosenDay\":\"2019-05-23\",\"chosenHour\":\"10:00\",\"clientId\":}";
        //WHEN
        mockMvc.perform(post("/api/appointments")
                .content(appointmentJSONwithWrongID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
        mockMvc.perform(post("/api/appointments")
                .content(appointmentJSONwithNoID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
        //THAN
        addNewClient(newClientInformationJson1);
        addNewAppointment(appointmentJSON);
        mockMvc.perform(get("/api/appointments"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(status().isOk());
    }
}
