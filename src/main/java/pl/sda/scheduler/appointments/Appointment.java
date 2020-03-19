package pl.sda.scheduler.appointments;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.sda.scheduler.clients.Client;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "APPOINTMENT")
public class Appointment {
    @Id
    @GeneratedValue
    @Column(name = "ID_APPOINTMENT")
    private long Id;
    @Column(name = "ID_CLIENT")
    private long clientId;
    @Column(name = "CHOSEN_DAY")
    private LocalDate chosenDay;
    @Column(name = "TREATMENT_NAME")
    private String nameOfTreatment;
    @Column(name = "HOUR_START")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime chosenHour;
    @Column(name = "HOUR_END")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hourFinished;
    @Column(name = "PRICE")
    private String price;
    private String clientData;
    @ManyToOne
    private Client client;

}