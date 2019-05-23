package pl.sda.scheduler.appointments;

import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/appointments")
class AppointmentsController {
    private AppointmentMapper appointmentMapper;
    private AppointmentsService appointmentsService;

    private AppointmentsController(AppointmentMapper appointmentMapper, AppointmentsService appointmentsService) {
        this.appointmentMapper = appointmentMapper;
        this.appointmentsService = appointmentsService;
    }

    @PostMapping
    private AppointmentDTO addNewAppointment(@RequestBody CreateNewAppointmentDTO newAppointmentDTO) {
        return appointmentMapper.DTO(appointmentsService.addNewAppointment(appointmentMapper.model(newAppointmentDTO)));
    }

    @GetMapping
    private Stream<AppointmentDTO> getAllAppointments() {
        return appointmentsService.getAllAppointments().stream().map(appointmentMapper::DTO);
    }
    @DeleteMapping
    @RequestMapping("/{id}")
    private void delateAppointmentById(@PathVariable long id){
        appointmentsService.deleteAppointmentById(id);
    }
    @GetMapping(params = "date")
    Stream<AppointmentDTO> getAllAppointmentsByDate(@RequestParam("date") String date){
        return appointmentsService.findAppointmentByDate(date).stream().map(appointmentMapper::DTO);
    }
}
