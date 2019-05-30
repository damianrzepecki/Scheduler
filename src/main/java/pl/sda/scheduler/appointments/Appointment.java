package pl.sda.scheduler.appointments;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
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
    @Column(name = "WYBRANA_GODZINA")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime chosenHour;
    @Column(name = "NAZWA_ZABIEGU")
    private String nameOfTreatment;
    @Column(name = "CENA")
    private String price;
    @ManyToOne
    private Client client;

}