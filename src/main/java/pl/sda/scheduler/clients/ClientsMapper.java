package pl.sda.scheduler.clients;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
class ClientsMapper {
    Clients model(CreateNewClientDTO createNewClientDTO) {
        Clients clients = new Clients();
        clients.setName(createNewClientDTO.getName());
        clients.setSurname(createNewClientDTO.getSurname());
        clients.setEmail(createNewClientDTO.getEmail());
        clients.setPhoneNumber(createNewClientDTO.getPhoneNumber());
        clients.setDateRegistered(LocalDate.now());
        return clients;
    }

    ClientsDTO DTO(Clients clients) {
        ClientsDTO clientsDTO = new ClientsDTO();
        clientsDTO.setId(clients.getId());
        clientsDTO.setName(clients.getName());
        clientsDTO.setSurname(clients.getSurname());
        clientsDTO.setPhoneNumber(clients.getPhoneNumber());
        clientsDTO.setEmail(clients.getEmail());
        clientsDTO.setDateRegistered(clients.getDateRegistered());
        return clientsDTO;
    }
}
