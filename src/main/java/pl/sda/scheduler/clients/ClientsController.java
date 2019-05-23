package pl.sda.scheduler.clients;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/clients")
class ClientsController {
    private ClientsService clientsService;
    private ClientsMapper clientsMapper;

    private ClientsController(ClientsService clientsService, ClientsMapper clientsMapper) {
        this.clientsService = clientsService;
        this.clientsMapper = clientsMapper;
    }

    @PostMapping
    private ClientsDTO addClient(@RequestBody CreateNewClientDTO newClientDTO) {
        return clientsMapper.DTO(clientsService.addNewClient(clientsMapper.model(newClientDTO)));
    }

    @GetMapping
    private Stream<ClientsDTO> getAllClients() {
        return clientsService.findAllClients().stream().map(clientsMapper::DTO);
    }

    @GetMapping("/{id}")
    private Optional<ClientsDTO> getClienyById(@PathVariable long id) {
        return clientsService.findById(id).map(clientsMapper::DTO);
    }

    @GetMapping(params = "surname")
    Stream<ClientsDTO> getClientBySurname(@RequestParam("surname") String surname) {
        return clientsService.findBySurname(surname).stream().map(clientsMapper::DTO);
    }

    @DeleteMapping("/{id}")
    private void delateByID(@PathVariable long id) {
        clientsService.deleteById(id);
    }

    @PutMapping("/{id}")
    private void updateClientData(@PathVariable long id, @RequestBody UpdateClientDataDTO clientUpdateDTO) {
        clientsService.updateAllClientData(id, clientsMapper.model(clientUpdateDTO));
    }
}
