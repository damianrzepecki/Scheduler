package pl.sda.scheduler.appointments;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AppointmentsService {
    private AppointmentsRepository appointmentsRepository;

    private AppointmentsService(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    Appointments addNewAppointment(Appointments appointments) {
        return appointmentsRepository.save(appointments);
    }

    List<Appointments> getAllAppointments() {
        return appointmentsRepository.findAll();
    }
}
