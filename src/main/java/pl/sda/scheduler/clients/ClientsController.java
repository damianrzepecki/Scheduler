package pl.sda.scheduler.clients;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/clients")
class ClientsController {
    private ClientsService clientsService;
    private ClientMapper clientMapper;

    private ClientsController(ClientsService clientsService, ClientMapper clientMapper) {
        this.clientsService = clientsService;
        this.clientMapper = clientMapper;
    }

    @PostMapping
    private ClientDTO addClient(@RequestBody CreateNewClientDTO newClientDTO) {
        return clientMapper.DTO(clientsService.addNewClient(clientMapper.model(newClientDTO)));
    }

    @GetMapping
    private Stream<ClientDTO> getAllClients() {
        return clientsService.findAllClients().stream().map(clientMapper::DTO);
    }

    @GetMapping("/{id}")
    private Optional<ClientDTO> getClienyById(@PathVariable long id) {
        return clientsService.findById(id).map(clientMapper::DTO);
    }

    @GetMapping(params = "surname")
    Stream<ClientDTO> getClientBySurname(@RequestParam("surname") String surname) {
        return clientsService.findBySurname(surname).stream().map(clientMapper::DTO);
    }

    @DeleteMapping("/{id}")
    private void delateByID(@PathVariable long id) {
        clientsService.deleteById(id);
    }

    @PutMapping("/{id}")
    private void updateClientData(@PathVariable long id, @RequestBody UpdateClientDataDTO clientUpdateDTO) {
        clientsService.updateAllClientData(id, clientMapper.model(clientUpdateDTO));
    }
}
