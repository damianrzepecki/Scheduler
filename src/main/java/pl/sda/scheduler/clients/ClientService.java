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

    Client addNewClient(Client client) {
        return clientRepository.save(client);
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
        clientRepository.findById(id).ifPresent(updatedClinet -> {
            updatedClinet.setName(newClient.getName());
            updatedClinet.setSurname(newClient.getSurname());
            updatedClinet.setDateOfBirth(newClient.getDateOfBirth());
            updatedClinet.setEmail(newClient.getEmail());
            updatedClinet.setPhoneNumber(newClient.getPhoneNumber());
            clientRepository.save(updatedClinet);
        });
    }
}


