package pl.sda.scheduler.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.sda.scheduler.appointments.AppointmentDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClientDTO{
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String dateOfBirth;
    @NotNull
    private int phoneNumber;
    @Email
    @NotNull
    private String email;
    @JsonIgnore
    @NotNull
    private LocalDate dateRegistered = LocalDate.now();
    @JsonIgnore

    private List<AppointmentDTO> appointments;
}
