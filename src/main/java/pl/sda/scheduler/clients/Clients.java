package pl.sda.scheduler.clients;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@Entity
public class Clients {
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "ID_KLIENTA")
    Long id;
    @Column(name = "IMIE")
    String name;
    @Column(name = "NAZWISKO")
    String surname;
    @Column(name = "NUMER_TELEFONU")
    int phoneNumber;
    @Column(name = "EMAIL")
    String email;
    @Column(name = "DATA_REJESTRACJI")
    LocalDate dateRegistered;
}
