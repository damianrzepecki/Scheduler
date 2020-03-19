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
    private void addNewAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        appointmentService.addNewAppointment(appointmentMapper.appointmentDTOtoAppointment(appointmentDTO));
    }

    @GetMapping
    private Stream<AppointmentDTO> getAllAppointments() {
        return appointmentService.getAllAppointments().stream().map(appointmentMapper::appointmentToAppointmentDTO);
    }
    @DeleteMapping
    @RequestMapping("/{id}")
    private void deleteAppointmentById(@PathVariable long id){
        appointmentService.deleteAppointmentById(id);
    }
    @GetMapping(params = "date")
    Stream<AppointmentDTO> getAllAppointmentsByDate(@RequestParam("date") String date){
        return appointmentService.findAppointmentByDate(date).stream().map(appointmentMapper::appointmentToAppointmentDTO);
    }
}
