package pl.sda.scheduler.clients;

import lombok.Data;

import java.time.LocalDate;

@Data
class CreateNewClientDTO {
    private String name;
    private String surname;
    private int phoneNumber;
    private String email;
    private LocalDate dateRegistered;
}
