package pl.sda.scheduler.appointments;

import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/appointments")
class AppointmentsController {
    private AppointmentsMapper appointmentsMapper;
    private AppointmentsService appointmentsService;

    AppointmentsController(AppointmentsMapper appointmentsMapper, AppointmentsService appointmentsService) {
        this.appointmentsMapper = appointmentsMapper;
        this.appointmentsService = appointmentsService;
    }

    @PostMapping
    AppointmentsDTO addNewAppointment(@RequestBody CreateNewAppointmentDTO newAppointmentDTO) {
        return appointmentsMapper.toDTO(appointmentsService.addNewAppointment(appointmentsMapper.modelFromDTO(newAppointmentDTO)));
    }

    @GetMapping
    Stream<AppointmentsDTO> getAllAppointments() {
        return appointmentsService.getAllAppointments().stream().map(appointmentsMapper::toDTO);
    }
}
