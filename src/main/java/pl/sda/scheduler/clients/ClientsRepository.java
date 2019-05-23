package pl.sda.scheduler.clients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

interface ClientsRepository extends JpaRepository<Clients, Long> {
    List<Clients> findBySurname(@Param("surname") String surname);
}
