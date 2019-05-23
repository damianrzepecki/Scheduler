package pl.sda.scheduler.appointments;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentsService {
    private AppointmentsRepository appointmentsRepository;

    AppointmentsService(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    Appointments addNewAppointment(Appointments modelFromDTO) {
        return appointmentsRepository.save(modelFromDTO);
    }

    List<Appointments> getAllAppointments() {
        return appointmentsRepository.findAll();
    }
}
