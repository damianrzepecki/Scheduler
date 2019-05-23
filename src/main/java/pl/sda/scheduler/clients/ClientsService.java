package pl.sda.scheduler.clients;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class ClientsService {
    private ClientsRepository clientsRepository;

    private ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    List<Clients> findAllClients() {
        return clientsRepository.findAll();
    }

    Clients addNewClient(Clients clients) {
        return clientsRepository.save(clients);
    }

    Optional<Clients> findById(long id) {
        return clientsRepository.findById(id);
    }

    List<Clients> findBySurname(String surname) {
        return clientsRepository.findBySurname(surname);
    }

    void deleteById(long id) {
        clientsRepository.deleteById(id);
    }

    void updateAllClientData(long id, Clients client) {
        clientsRepository.findById(id).ifPresent(clients -> {
            clients.setName(client.getName());
            clients.setSurname(client.getSurname());
            clients.setEmail(client.getEmail());
            clients.setPhoneNumber(client.getPhoneNumber());
            clientsRepository.save(clients);
        });
    }
}

