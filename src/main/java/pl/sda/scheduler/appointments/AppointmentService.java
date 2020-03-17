package pl.sda.scheduler.appointments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public
class AppointmentService {

    private AppointmentRepository appointmentRepository;

    private AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    Appointment addNewAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    Page<Appointment> findAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }


    void deleteAppointmentById(long id) {
        appointmentRepository.deleteById(id);
    }

    List<Appointment> findAppointmentByDate(String dateString) {
        LocalDate dateType = LocalDate.parse(dateString);
        return appointmentRepository.findByChosenDay(dateType);
    }

    Optional<Appointment> findById(long id) {
        return appointmentRepository.findById(id);
    }

    void updateAppointment(Appointment newAppointment) {
        appointmentRepository.findById(newAppointment.getId()).ifPresent(updatedAppointment -> {
            updatedAppointment.setChosenDay(newAppointment.getChosenDay());
            updatedAppointment.setChosenHour(newAppointment.getChosenHour());
            updatedAppointment.setNameOfTreatment(newAppointment.getNameOfTreatment());
            updatedAppointment.setHourFinished(newAppointment.getHourFinished());
            updatedAppointment.setPrice(newAppointment.getPrice());
            updatedAppointment.setClientId(newAppointment.getClientId());
            appointmentRepository.save(updatedAppointment);
        });
    }
}
