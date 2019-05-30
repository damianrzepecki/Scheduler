package pl.sda.scheduler.appointments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sda.scheduler.configurations.PageWrapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public
class AppointmentsService {

    private AppointmentsRepository appointmentsRepository;

    private AppointmentsService(AppointmentsRepository appointmentsRepository) {
        this.appointmentsRepository = appointmentsRepository;
    }

    Appointment addNewAppointment(Appointment appointment) {
        return appointmentsRepository.save(appointment);
    }

    Page<Appointment> findAllAppointments(Pageable pageable) {
        return appointmentsRepository.findAll(pageable);
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

    Optional<Appointment> findById(long id) {
        return appointmentsRepository.findById(id);
    }

    void updateAppointment(long id, Appointment appointment) {
        appointmentsRepository.findById(id).ifPresent(appo -> {
            appo.setChosenDay(appointment.getChosenDay());
            appo.setChosenHour(appointment.getChosenHour());
            appo.setNameOfTreatment(appointment.getNameOfTreatment());
            appo.setPrice(appointment.getPrice());
            appo.setClientId(appointment.getClientId());
            appointmentsRepository.save(appo);
        });
    }
}
