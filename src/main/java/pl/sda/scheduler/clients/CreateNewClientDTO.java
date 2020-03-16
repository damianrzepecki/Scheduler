package pl.sda.scheduler.clients;

import lombok.Data;

import javax.validation.constraints.Email;
import java.time.LocalDate;

@Data
class CreateNewClientDTO {
    private String name;
    private String surname;
    private String dateOfBirth;
    private int phoneNumber;
    @Email
    private String email;
    private LocalDate dateRegistered;
}