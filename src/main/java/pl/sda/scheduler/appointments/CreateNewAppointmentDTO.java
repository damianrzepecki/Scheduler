package pl.sda.scheduler.appointments;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;

@Data
class  CreateNewAppointmentDTO {

    private String chosenDay;
    private String nameOfTreatment;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime chosenHour;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hourFinished;
    private String price;
    private long clientId;
}
