package pl.sda.scheduler.clients;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
class ClientMapperOld {
    Client model(CreateNewClientDTO createNewClientDTO) {
        Client client = new Client();
        client.setName(createNewClientDTO.getName());
        client.setSurname(createNewClientDTO.getSurname());
        client.setDateOfBirth((LocalDate.parse(createNewClientDTO.getDateOfBirth())));
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
        clientDTO.setDateOfBirth(String.valueOf(client.getDateOfBirth()));
        clientDTO.setPhoneNumber(client.getPhoneNumber());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setDateRegistered(client.getDateRegistered());
        return clientDTO;
    }
}
