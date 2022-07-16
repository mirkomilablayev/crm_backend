package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.SubjectStatus;

import java.util.Optional;

public interface SubjectStatusRepo extends JpaRepository<SubjectStatus, Long> {
    Optional<SubjectStatus> findByName(String name);
}
