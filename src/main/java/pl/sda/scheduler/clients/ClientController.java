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
    public String getClientList(Model model, Pageable pageable, ClientDTO clientDTO) {
        Page<ClientDTO> clientsPage = clientService.getAllClients(pageable).map(clientMapper::clientToClientDTO);
        model.addAttribute("page", clientsPage);
        model.addAttribute("clients", clientsPage.getContent());
        model.addAttribute("clientDTO", clientDTO);

        return "client/clients";
    }

    @PostMapping("/save")
    String saveClient(@Valid @ModelAttribute("clientDTO") ClientDTO clientDTO, BindingResult bindingResult) {
        Client clientExists = clientService.findByEmail(clientDTO.getEmail());
        System.out.println(clientExists);
        if (clientExists != null) {
            bindingResult.rejectValue("email", "email");
        }
        if (bindingResult.hasErrors()) {
            System.out.println("================ERROR================");
            System.out.println(bindingResult);
            return "client/clients :: addNewClient";
        } else {
            clientService.addNewClient(clientMapper.clientDTOtoClient(clientDTO));
        }
        return "redirect:/app/clients";
    }

    @PostMapping("/update")
    String updateClientData(@Valid @ModelAttribute("clientDTO") ClientDTO clientDTO, BindingResult bindingResult) {
        Client clientExists = clientService.findByEmail(clientDTO.getEmail());
        Optional<Client> clientCurrent = clientService.findById(clientDTO.getId());
        if(clientCurrent.get().getEmail().equals(clientExists.getEmail())){
            clientExists=null;
        }
        System.out.println(clientExists);
        if (clientExists != null) {
            bindingResult.rejectValue("email", "email");
        }
        if (bindingResult.hasErrors()) {
            System.out.println("================ERROR================");
            System.out.println(bindingResult);
            return "client/clients :: updateClient";
        } else {
            clientService.updateAllClientData(clientMapper.clientDTOtoClient(clientDTO));
        }
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