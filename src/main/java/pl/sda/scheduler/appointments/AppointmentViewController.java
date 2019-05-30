package pl.sda.scheduler.appointments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sda.scheduler.configurations.PageWrapper;

import java.util.Optional;

@Controller
@RequestMapping("/app/appointments")
public class AppointmentViewController {
    private AppointmentsService appointmentsService;
    private AppointmentMapper appointmentMapper;

    public AppointmentViewController(AppointmentsService appointmentsService, AppointmentMapper appointmentMapper) {
        this.appointmentsService = appointmentsService;
        this.appointmentMapper = appointmentMapper;
    }

    @GetMapping
    public String getAppointmentList(Model model, Pageable pageable) {
        Page<AppointmentDTO> appointmentPage = appointmentsService.findAllAppointments(pageable).map(appointmentMapper::DTO);
        PageWrapper<AppointmentDTO> page = new PageWrapper<>(appointmentPage, "/app/appointments");
        model.addAttribute("page", page);
        model.addAttribute("appointments", page.getContent());
        return "appointment/appointments";
    }

    @PostMapping("/save")
    public String saveAppointment(CreateNewAppointmentDTO newAppointmentDTO) {
        appointmentMapper.DTO(appointmentsService.addNewAppointment(appointmentMapper.model(newAppointmentDTO)));
        return "redirect:/app/clients";
    }

    @GetMapping("/findOne")
    @ResponseBody
    Optional<AppointmentDTO> findOne(long id) {
        return appointmentsService.findById(id).map(appointmentMapper::DTO);
    }

    @GetMapping("/delete")
    String deleteAppointment(long id) {
        appointmentsService.deleteAppointmentById(id);
        return "redirect:/app/appointments";
    }

    @PostMapping("/update")
    String updateAppointmentData(long id, UpdateAppointmentDTO updateAppointmentDTO) {
        appointmentsService.updateAppointment(id, appointmentMapper.model(updateAppointmentDTO));
        return "redirect:/app/appointments";
    }
}
