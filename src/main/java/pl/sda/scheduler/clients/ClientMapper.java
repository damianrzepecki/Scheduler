package pl.sda.scheduler.clients;

import org.springframework.stereotype.Component;
import pl.sda.scheduler.appointments.Appointment;

import java.time.LocalDate;

@Component
class ClientMapper {
    Client model(CreateNewClientDTO createNewClientDTO) {
        Client client = new Client();
        Appointment appointment = new Appointment();
        client.setName(createNewClientDTO.getName());
        client.setSurname(createNewClientDTO.getSurname());
        client.setEmail(createNewClientDTO.getEmail());
        client.setPhoneNumber(createNewClientDTO.getPhoneNumber());
        client.setDateRegistered(LocalDate.now());
        return client;
    }

    ClientDTO DTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setSurname(client.getSurname());
        clientDTO.setPhoneNumber(client.getPhoneNumber());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setDateRegistered(client.getDateRegistered());
        return clientDTO;
    }
}
