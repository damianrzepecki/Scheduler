package pl.sda.scheduler.clients;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
class ClientsController {
    ClientsRepository clientsRepository;

    ClientsController(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @GetMapping
    Iterable<Clients> getAllClients() {
        return clientsRepository.findAll();
    }

    @PostMapping
    Clients addClient(@RequestBody Clients clients) {
        clients.setDateRegistered(LocalDate.now());
        return clientsRepository.save(clients);
    }

    @GetMapping("/{id}")
    Optional<Clients> getClienyById(@PathVariable long id) {
        return clientsRepository.findById(id);
    }

    @GetMapping(params = "surname")
    List<Clients> getClientBySurname(@RequestParam("surname") String surname) {
        return clientsRepository.findBySurname(surname);

    }

    @DeleteMapping("/{id}")
    void delateByID(@PathVariable long id) {
        clientsRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    void updateClientData(@PathVariable long id, @RequestBody Clients newClients) {
        clientsRepository.findById(id).ifPresent(clients -> {
            clients.setName(newClients.getName());
            clients.setSurname(newClients.getSurname());
            clients.setEmail(newClients.getEmail());
            clients.setPhoneNumber(newClients.getPhoneNumber());
            clientsRepository.save(clients);
        });
    }

}
