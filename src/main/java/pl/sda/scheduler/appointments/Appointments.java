package pl.sda.scheduler.appointments;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import pl.sda.scheduler.clients.Clients;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
//TODO Eliminate the possibility to add Appointment even if there is not Client or bad ID _ It takes Appointment ID
class Appointments {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long Id;
    private long clientId;
    private LocalDate chosenDay;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime chosenHour;
    @ManyToOne
    private Clients clients;

}