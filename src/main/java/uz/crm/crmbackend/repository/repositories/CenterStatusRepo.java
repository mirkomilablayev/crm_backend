package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.CenterStatus;

import java.util.Optional;

public interface CenterStatusRepo extends JpaRepository<CenterStatus,Long> {
    Optional<CenterStatus> findByName(String name);
}
