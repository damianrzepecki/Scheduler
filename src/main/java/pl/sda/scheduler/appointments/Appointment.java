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
@Table(name = "TERMIN")
public class Appointment {
    @Id
    @GeneratedValue
    @Column(name = "ID_TERMINU")
    private long Id;
    @Column(name = "ID_KLIENTA")
    private long clientId;
    @Column(name = "WYBRANY_DZIEN")
    private LocalDate chosenDay;
    @Column(name = "NAZWA_ZABIEGU")
    private String nameOfTreatment;
    @Column(name = "GODZINA_ROZPOCZĘCIA")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime chosenHour;
    @Column(name = "GODZINA_ZAKOŃCZENIA")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hourFinished;
    @Column(name = "CENA")
    private String price;

    private String clientData;
    @ManyToOne
    private Client client;

}