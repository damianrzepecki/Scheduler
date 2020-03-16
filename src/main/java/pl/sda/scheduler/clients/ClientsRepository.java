package pl.sda.scheduler.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface ClientsRepository extends JpaRepository<Client, Long> {
    List<Client> findBySurname(@Param("surname") String surname);
    Client findByEmail(String email);
}
