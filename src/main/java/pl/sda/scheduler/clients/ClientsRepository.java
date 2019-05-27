package pl.sda.scheduler.clients;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import java.util.List;

interface ClientsRepository extends JpaRepository<Client, Long> {
    Page<Client> findAll(Pageable pageable);
    List<Client> findBySurname(@Param("surname") String surname);
}
