package pl.sda.scheduler.clients;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sda.scheduler.appointments.AppointmentDTO;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/app/clients")
public class ClientController {
    private ClientService clientService;
    private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
    private CustomClientValidation customClientValidation;

    ClientController(ClientService clientService, CustomClientValidation customClientValidation) {
        this.clientService = clientService;
        this.customClientValidation = customClientValidation;
    }

    @GetMapping
    public String getClientList(Model model, Pageable pageable, ClientDTO clientDTO, AppointmentDTO appointmentDTO) {
        Page<ClientDTO> clientsPage = clientService.getAllClients(pageable).map(clientMapper::clientToClientDTO);
        model.addAttribute("page", clientsPage);
        model.addAttribute("clients", clientsPage.getContent());
        model.addAttribute("clientDTO", clientDTO);
        model.addAttribute("appointmentDTO", appointmentDTO);

        return "client/clients";
    }

    @PostMapping("/save")
    String saveClient(@Valid @ModelAttribute("clientDTO") ClientDTO clientDTO, BindingResult bindingResult) {
        if (customClientValidation.isEmailTaken(clientDTO)) {
            bindingResult.rejectValue("email", "email");
        }
        if (bindingResult.hasErrors()) {
            System.out.println("================SAVE=ERROR================");
            System.out.println(bindingResult);
            return "client/clients :: addNewClient";
        }
        else {
            clientService.addNewClient(clientMapper.clientDTOtoClient(clientDTO));
        }
        return "redirect:/app/clients";
    }

    @PostMapping("/update")
    String updateClientData(@Valid @ModelAttribute("clientDTO") ClientDTO clientDTO, BindingResult bindingResult) {
        if (customClientValidation.isEmailTakenBySomeoneElse(clientDTO)) {
            bindingResult.rejectValue("email", "email");
        }
        if (bindingResult.hasErrors()) {
            System.out.println("================UPDATE=ERROR================");
            System.out.println(bindingResult);
            return "client/clients :: updateClient";
        }
        else {
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