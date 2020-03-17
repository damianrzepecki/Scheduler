package pl.sda.scheduler.clients;

import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/clients")
class ClientControllerForApiAndTests {
    private ClientService clientService;
    private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

    private ClientControllerForApiAndTests(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    private ClientDTO addClient(@RequestBody ClientDTO clientDTO) {
        return clientMapper.clientToClientDTO(clientService.addNewClient(clientMapper.clientDTOtoClient(clientDTO)));
    }

    @GetMapping
    private Stream<ClientDTO> getAllClients() {
        return clientService.findAllClients().stream().map(clientMapper::clientToClientDTO);
    }

    @GetMapping("/{id}")
    private Optional<ClientDTO> getClientById(@PathVariable long id) {
        return clientService.findById(id).map(clientMapper::clientToClientDTO);
    }

    @GetMapping(params = "surname")
    Stream<ClientDTO> getClientBySurname(@RequestParam("surname") String surname) {
        return clientService.findBySurname(surname).stream().map(clientMapper::clientToClientDTO);
    }

    @DeleteMapping("/{id}")
    private void deleteByID(@PathVariable long id) {
        clientService.deleteById(id);
    }

    @PutMapping("/{id}")
    private void updateClientData(@RequestBody ClientDTO clientDTO) {
        clientService.updateAllClientData(clientMapper.clientDTOtoClient(clientDTO));
    }
}
