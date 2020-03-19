package pl.sda.scheduler.clients;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/app/clients")
public class ClientController {
    private ClientService clientService;
    private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String getClientList(Model model, Pageable pageable) {
        Page<ClientDTO> clientsPage = clientService.getAllClients(pageable).map(clientMapper::clientToClientDTO);
        model.addAttribute("page", clientsPage);
        model.addAttribute("clients", clientsPage.getContent());
        return "client/clients";
    }

    @PostMapping("/save")
    String saveClient(@Valid @ModelAttribute("client") ClientDTO clientDTO, BindingResult bindingResult) {
        Client clientExists = clientService.findByEmail(clientDTO.getEmail());
        if (clientExists != null) {
            bindingResult.reject("email");

        } else
            clientService.addNewClient(clientMapper.clientDTOtoClient(clientDTO));
        return "redirect:/app/clients";
    }

    @PostMapping("/update")
    String updateClientData(ClientDTO clientDTO) {
        clientService.updateAllClientData(clientMapper.clientDTOtoClient(clientDTO));
        return "redirect:/app/clients";
    }

    @GetMapping("/delete")
    String deleteClient(long id) {
        clientService.deleteById(id);
        return "redirect:/app/clients";
    }

    @GetMapping("/findOne")
    @ResponseBody
    Optional<ClientDTO> findOne(long id) {
        return clientService.findById(id).map(clientMapper::clientToClientDTO);
    }
}