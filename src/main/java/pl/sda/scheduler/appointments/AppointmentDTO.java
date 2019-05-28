package pl.sda.scheduler.appointments;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
class AppointmentDTO {
    private long id;
    private LocalDate chosenDay;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime chosenHour;
    private String nameOfTreatment;
    private String price;
    private long clientId;
    private String clientData;
}
