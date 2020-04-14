package pl.sda.scheduler.clients;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.sda.scheduler.appointments.AppointmentDTO;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClientDTO {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Size(min = 2, max = 20)
    private String name;
    @NotNull
    @Size(min = 2, max = 20)
    private String surname;
    @NotNull
    private String dateOfBirth;
    @NotNull
    private int phoneNumber;
    @Email(message = "TAKEN")
    @NotNull
    private String email;
    @JsonIgnore
    @NotNull
    private LocalDate dateRegistered = LocalDate.now();
    @JsonIgnore
    private List<AppointmentDTO> appointmentArrayList;


}
