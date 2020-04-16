package pl.sda.scheduler.appointments;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.scheduler.clients.ClientDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Data
public class AppointmentDTO {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String chosenDay;
    @NotNull
    @Size(min = 2, max = 20)
    private String nameOfTreatment;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime chosenHour;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hourFinished;
    @NotNull
    private String price;
    @NotNull
    private long clientId;
    private String clientData;
    private ClientDTO client;

}
