package pl.sda.scheduler.clients;

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
public class ClientsViewController {
    private ClientsService clientsService;
    private ClientMapper clientMapper;

    ClientsViewController(ClientsService clientsService, ClientMapper clientMapper) {
        this.clientsService = clientsService;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public String getClientList(Model model, Pageable pageable) {
        Page<ClientDTO> clientsPage = clientsService.getAllClients(pageable).map(clientMapper::DTO);
        model.addAttribute("page", clientsPage);
        model.addAttribute("clients", clientsPage.getContent());
        return "client/clients";
    }

    @PostMapping("/save")
    String saveClient(@Valid @ModelAttribute("client") BindingResult bindingResult, CreateNewClientDTO createNewClientDTO) {
        Client clientExists = clientsService.findByEmail(createNewClientDTO.getEmail());
        if (clientExists != null) {
            bindingResult.reject("email");

        } else
            clientMapper.DTO(clientsService.addNewClient(clientMapper.model(createNewClientDTO)));
        return "redirect:/app/clients";
    }

    @PostMapping("/update")
    String updateClientData(long id, UpdateClientDataDTO clientUpdateDTO) {
        clientsService.updateAllClientData(id, clientMapper.model(clientUpdateDTO));
        return "redirect:/app/clients";
    }

    @GetMapping("/delete")
    String deleteClient(long id) {
        clientsService.deleteById(id);
        return "redirect:/app/clients";
    }

    @GetMapping("/findOne")
    @ResponseBody
    Optional<ClientDTO> findOne(long id) {
        return clientsService.findById(id).map(clientMapper::DTO);
    }
}