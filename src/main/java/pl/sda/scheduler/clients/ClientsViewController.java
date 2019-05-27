package pl.sda.scheduler.clients;

import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.scheduler.Configurations.PageWrapper;

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
        PageWrapper<ClientDTO> page = new PageWrapper<>(clientsPage, "/app/clients");
        model.addAttribute("page", page);
        model.addAttribute("clients", page.getContent());
        return "clients";
    }
    //TODO is save goot for both post and put? CreateNewClientDTO
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

    @DeleteMapping("/delete")
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