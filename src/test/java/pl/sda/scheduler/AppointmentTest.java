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
import pl.sda.scheduler.appointments.Appointment;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ActiveProfiles("test")
class AppointmentTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private String newClientInformationJson1 = "{\"name\":\"John\",\"surname\":\"Doe\",\"dateOfBirth\":\"2000-04-02\",\"phoneNumber\":123456789,\"email\":\"jakis@email.com\"}";
    private String appointmentJSON = "{\"chosenDay\":\"2019-05-23\",\"chosenHour\":\"10:00\",\"price\":\"125\",\"nameOfTreatment\":\"Mikrodermabrazja\",\"clientId\":1}";

    private void addNewClient(String client) throws Exception {
        mockMvc.perform(post("/api/clients")
                .content(client).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Long addNewAppointment(String appointment) throws Exception {
        String createdAppointmentString = mockMvc.perform(post("/api/appointments")
                .content(appointment)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();
        return objectMapper.readValue(createdAppointmentString, Appointment.class).getId();
    }

    private void addAppointmentToClient(String client, String appointment) throws Exception {
        addNewClient(client);
        addNewAppointment(appointment);
    }

    @DisplayName("Test if GET is giving status OK")
    @Test
    void test1() throws Exception {
        //WHEN
        mockMvc.perform(get("/api/appointments"))
                //THAN
                .andExpect(status().isOk());
    }

    @DisplayName("Add new Appointment to chosen day with user")
    @Test
    void test2() throws Exception {
        //GIVEN
        //String appointmentJSON and newClientInformationJson1
        //WHEN
        addNewClient(newClientInformationJson1);
        addNewAppointment(appointmentJSON);
        //THAN
        mockMvc.perform(get("/api/appointments"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].chosenDay", is("2019-05-23")))
                .andExpect(jsonPath("$[0].chosenHour", is("10:00")))
                .andExpect(jsonPath("$[0].nameOfTreatment", is("Mikrodermabrazja")))
                .andExpect(jsonPath("$[0].price", is("125")))
                .andExpect(jsonPath("$[0].clientId", is(1)))
                .andExpect(status().isOk());
    }

    @DisplayName("Delete Appointment by Id")
    @Test
    void test3() throws Exception {
        //GIVEN
        addNewClient(newClientInformationJson1);
        long id = addNewAppointment(appointmentJSON);
        //WHEN
        mockMvc.perform(delete("/api/appointments/{id}", id)).andExpect(status()
                .isOk());
        //THAN
        mockMvc.perform(get("/api/appointments"))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }

    @DisplayName("Find Appointment By Date")
    @Test
    void test4() throws Exception {
        //GIVEN
        addAppointmentToClient(newClientInformationJson1, appointmentJSON);
        addAppointmentToClient(newClientInformationJson1, appointmentJSON);
        addAppointmentToClient(newClientInformationJson1, appointmentJSON);
        //WHEN
        mockMvc.perform(get("/api/appointments")
                .param("date", "2019-05-23")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //THAN
                .andExpect(jsonPath("$[*].clientData", hasSize(3)));
    }
}