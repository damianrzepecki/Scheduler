package pl.sda.scheduler.clients;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomClientValidation {

    ClientService clientService;

    public CustomClientValidation(ClientService clientService) {
        this.clientService = clientService;
    }

    public boolean isEmailTaken(ClientDTO clientDTO) {
        Client clientExists = clientService.findByEmail(clientDTO.getEmail());
        return clientExists != null;
    }

    public boolean isEmailTakenBySomeoneElse(ClientDTO clientDTO) {
        Client clientExists = clientService.findByEmail(clientDTO.getEmail());
        Optional<Client> clientCurrent = clientService.findById(clientDTO.getId());
        if (clientCurrent.isPresent()) {
            if (clientCurrent.get().getEmail().equals(clientDTO.getEmail())) {
                clientExists = null;
            }
        }
        return clientExists != null;
    }
}
