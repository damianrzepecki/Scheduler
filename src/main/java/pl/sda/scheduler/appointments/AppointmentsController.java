package pl.sda.scheduler.appointments;

import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/appointments")
class AppointmentsController {
    private AppointmentsMapper appointmentsMapper;
    private AppointmentsService appointmentsService;

    private AppointmentsController(AppointmentsMapper appointmentsMapper, AppointmentsService appointmentsService) {
        this.appointmentsMapper = appointmentsMapper;
        this.appointmentsService = appointmentsService;
    }

    @PostMapping
    private AppointmentsDTO addNewAppointment(@RequestBody CreateNewAppointmentDTO newAppointmentDTO) {
        return appointmentsMapper.DTO(appointmentsService.addNewAppointment(appointmentsMapper.model(newAppointmentDTO)));
    }

    @GetMapping
    private Stream<AppointmentsDTO> getAllAppointments() {
        return appointmentsService.getAllAppointments().stream().map(appointmentsMapper::DTO);
    }
}
