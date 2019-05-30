package pl.sda.scheduler.appointments;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByChosenDay(@Param("date") LocalDate date);
    Page<Appointment> findAll (Pageable pageable);
}
