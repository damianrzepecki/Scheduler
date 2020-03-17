package pl.sda.scheduler.clients;

import lombok.Data;
import pl.sda.scheduler.appointments.Appointment;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "CLIENT_DATA")
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "CLIENT_ID")
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "PHONE_NUMBER")
    private int phoneNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DATE_REGISTERED")
    private LocalDate dateRegistered;
    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Appointment> appointment;
}
