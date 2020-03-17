package pl.sda.scheduler.appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByChosenDay(@Param("date") LocalDate date);

}
