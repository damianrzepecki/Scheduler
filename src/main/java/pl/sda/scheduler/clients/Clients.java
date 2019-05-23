package pl.sda.scheduler.clients;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Clients {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "ID_KLIENTA")
    private long id;
    @Column(name = "IMIE")
    private String name;
    @Column(name = "NAZWISKO")
    private String surname;
    @Column(name = "NUMER_TELEFONU")
    private int phoneNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DATA_REJESTRACJI")
    private LocalDate dateRegistered;
}
