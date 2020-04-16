package pl.sda.scheduler.appointments;

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
@RequestMapping("/app/appointments")
public class AppointmentController {
    private AppointmentService appointmentService;
    private AppointmentMapper appointmentMapper = Mappers.getMapper(AppointmentMapper.class);

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public String getAppointmentList(Model model, Pageable pageable, AppointmentDTO appointmentDTO) {
        Page<AppointmentDTO> appointmentPage = appointmentService.findAllAppointments(pageable).map(appointmentMapper::appointmentToAppointmentDTO);
        model.addAttribute("page", appointmentPage);
        model.addAttribute("appointments", appointmentPage.getContent());
        model.addAttribute("appointmentDTO", appointmentDTO);
        return "appointment/appointments";
    }

    @PostMapping("/save")
    public String saveAppointment(@Valid @ModelAttribute("appointmentDTO") AppointmentDTO appointmentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("================SAVE=ERROR================");
            System.out.println(bindingResult);
            return "client/clients :: addAppointment";
        } else {
            appointmentService.addNewAppointment(appointmentMapper.appointmentDTOtoAppointment(appointmentDTO));
        }
        return "redirect:/app/clients";
    }

    @PostMapping("/update")
    String updateAppointmentData(@Valid @ModelAttribute("appointmentDTO") AppointmentDTO appointmentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("================UPDATE=ERROR================");
            System.out.println(bindingResult);
            return "appointment/appointments :: updateAppointment";
        } else {
            appointmentService.updateAppointment(appointmentMapper.appointmentDTOtoAppointment(appointmentDTO));
        }
        return "redirect:/app/appointments";
    }

    @GetMapping("/findOne")
    @ResponseBody
    Optional<AppointmentDTO> findOne(long id) {
        return appointmentService.findById(id).map(appointmentMapper::appointmentToAppointmentDTO);
    }

    @GetMapping("/delete")
    String deleteAppointment(long id) {
        appointmentService.deleteAppointmentById(id);
        return "redirect:/app/appointments";
    }

}
