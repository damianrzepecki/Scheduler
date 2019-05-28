package pl.sda.scheduler.appointments;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import pl.sda.scheduler.clients.Client;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public  class Appointment {
    @Id
    @GeneratedValue
    private long Id;
    private long clientId;
    private LocalDate chosenDay;
    private String nameOfTreatment;
    private String price;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime chosenHour;
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

}