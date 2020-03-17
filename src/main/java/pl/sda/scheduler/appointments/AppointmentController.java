package pl.sda.scheduler.appointments;

import org.mapstruct.factory.Mappers;
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
@RequestMapping("/app/appointments")
public class AppointmentController {
    private AppointmentService appointmentService;
    private AppointmentMapper appointmentMapper = Mappers.getMapper(AppointmentMapper.class);

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public String getAppointmentList(Model model, Pageable pageable) {
        Page<AppointmentDTO> appointmentPage = appointmentService.findAllAppointments(pageable).map(appointmentMapper::appointmentToAppointmentDTO);
        model.addAttribute("page", appointmentPage);
        model.addAttribute("appointments", appointmentPage.getContent());
        return "appointment/appointments";
    }

    @PostMapping("/save")
    public String saveAppointment(AppointmentDTO appointmentDTO) {
        appointmentService.addNewAppointment(appointmentMapper.appointmentDTOtoAppointment(appointmentDTO));
        return "redirect:/app/clients";
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

    @PostMapping("/update")
    String updateAppointmentData(AppointmentDTO appointmentDTO) {
        appointmentService.updateAppointment(appointmentMapper.appointmentDTOtoAppointment(appointmentDTO));
        return "redirect:/app/appointments";
    }
}
