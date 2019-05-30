package pl.sda.scheduler.clients;

import lombok.Data;
import pl.sda.scheduler.appointments.Appointment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue
    @Column(name = "ID_KLIENTA")
    private long id;
    @Column(name = "IMIE")
    private String name;
    @Column(name = "NAZWISKO")
    private String surname;
    @Column(name = "DATA_URODZENIA")
    private LocalDate dateOfBirth;
    @Column(name = "NUMER_TELEFONU")
    private int phoneNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DATA_REJESTRACJI")
    private LocalDate dateRegistered;
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Appointment> appointment;
}
