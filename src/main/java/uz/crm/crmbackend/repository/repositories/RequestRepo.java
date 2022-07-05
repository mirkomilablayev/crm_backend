package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.RequestToGetDemo;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepo extends JpaRepository<RequestToGetDemo,Long>, BaseRepository {
    Optional<RequestToGetDemo> findByIdAndIsActive(Long id, Boolean isActive);
    List<RequestToGetDemo> findAllByIsActive(Boolean isActive);
}
