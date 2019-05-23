package pl.sda.scheduler.appointments;

import org.springframework.data.jpa.repository.JpaRepository;

interface AppointmentsRepository extends JpaRepository<Appointments, Long> {
}
