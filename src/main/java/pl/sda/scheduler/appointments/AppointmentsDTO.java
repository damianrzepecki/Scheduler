package pl.sda.scheduler.appointments;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
class AppointmentsDTO {
    private long appointmentId;
    private LocalDate chosenDay;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime chosenHour;
    private long clientId;
    private String clientData;
}
