package pl.sda.scheduler.clients;

import lombok.Data;

import java.time.LocalDate;

@Data
class ClientsDTO {
    private long id;
    private String name;
    private String surname;
    private int phoneNumber;
    private String email;
    private LocalDate dateRegistered;
}
