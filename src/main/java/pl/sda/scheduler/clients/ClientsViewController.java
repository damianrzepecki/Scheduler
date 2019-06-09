package pl.sda.scheduler.clients;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    String saveClient(CreateNewClientDTO createNewClientDTO) {
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