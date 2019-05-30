package pl.sda.scheduler.appointments;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
class  CreateNewAppointmentDTO {

    private String chosenDay;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime chosenHour;
    private String nameOfTreatment;
    private String price;
    private long clientId;
}
