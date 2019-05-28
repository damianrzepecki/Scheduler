package pl.sda.scheduler.clients;

import lombok.Data;

import java.time.LocalDate;

@Data
class ClientDTO {
    private long id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private int phoneNumber;
    private String email;
    private LocalDate dateRegistered;
}
