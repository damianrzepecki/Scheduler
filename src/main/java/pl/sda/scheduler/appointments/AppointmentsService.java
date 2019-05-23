package pl.sda.scheduler.appointments;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
class AppointmentsService {
    private AppointmentsRepository appointmentsRepository;

    private AppointmentsService(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    Appointment addNewAppointment(Appointment appointment) {
        return appointmentsRepository.save(appointment);
    }

    List<Appointment> getAllAppointments() {
        return appointmentsRepository.findAll();
    }

    void deleteAppointmentById(long id) {
        appointmentsRepository.deleteById(id);
    }

    List<Appointment> findAppointmentByDate(String dateString) {
        LocalDate dateType = LocalDate.parse(dateString);
        return appointmentsRepository.findByChosenDay(dateType);
    }
}
