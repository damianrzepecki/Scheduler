package pl.sda.scheduler.clients;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class ClientsService {
    private ClientsRepository clientsRepository;

    private ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    Page<Client> getAllClients(Pageable pageable) {
        return clientsRepository.findAll(pageable);
    }

    List<Client> findAllClients() {
        return clientsRepository.findAll();
    }

    Client addNewClient(Client client) {
        return clientsRepository.save(client);
    }

    Optional<Client> findById(long id) {
        return clientsRepository.findById(id);
    }

    List<Client> findBySurname(String surname) {
        return clientsRepository.findBySurname(surname);
    }

    void deleteById(long id) {
        clientsRepository.deleteById(id);
    }

    void updateAllClientData(long id, Client client) {
        clientsRepository.findById(id).ifPresent(clients -> {
            clients.setName(client.getName());
            clients.setSurname(client.getSurname());
            clients.setDateOfBirth(client.getDateOfBirth());
            clients.setEmail(client.getEmail());
            clients.setPhoneNumber(client.getPhoneNumber());
            clientsRepository.save(clients);
        });
    }
}

