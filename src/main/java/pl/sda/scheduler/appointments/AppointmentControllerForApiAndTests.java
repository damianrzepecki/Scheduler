package pl.sda.scheduler.appointments;

import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api/appointments")
class AppointmentControllerForApiAndTests {
    private AppointmentService appointmentService;
    private AppointmentMapper appointmentMapper = Mappers.getMapper(AppointmentMapper.class);

    private AppointmentControllerForApiAndTests(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    private AppointmentDTO addNewAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentMapper.appointmentToAppointmentDTO(appointmentService.addNewAppointment(appointmentMapper.appointmentDTOtoAppointment(appointmentDTO)));
    }

    @GetMapping
    private Stream<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAllAppointments().stream().map(appointmentMapper::appointmentToAppointmentDTO);
    }
    @DeleteMapping
    @RequestMapping("/{id}")
    private void delateAppointmentById(@PathVariable long id){
        appointmentService.deleteAppointmentById(id);
    }
    @GetMapping(params = "date")
    Stream<AppointmentDTO> getAllAppointmentsByDate(@RequestParam("date") String date){
        return appointmentService.findAppointmentByDate(date).stream().map(appointmentMapper::appointmentToAppointmentDTO);
    }
}
