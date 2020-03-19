package pl.sda.scheduler.clients;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class ClientService {
    private ClientRepository clientRepository;

    private ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    Page<Client> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    void addNewClient(Client client) {
        clientRepository.save(client);
    }

    Optional<Client> findById(long id) {
        return clientRepository.findById(id);
    }

    List<Client> findBySurname(String surname) {
        return clientRepository.findBySurname(surname);
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    void deleteById(long id) {
        clientRepository.deleteById(id);
    }

    void updateAllClientData(Client newClient) {
        long id = newClient.getId();
        clientRepository.findById(id).ifPresent(updatedClient -> {
            updatedClient.setName(newClient.getName());
            updatedClient.setSurname(newClient.getSurname());
            updatedClient.setDateOfBirth(newClient.getDateOfBirth());
            updatedClient.setEmail(newClient.getEmail());
            updatedClient.setPhoneNumber(newClient.getPhoneNumber());
            clientRepository.save(updatedClient);
        });
    }
}


