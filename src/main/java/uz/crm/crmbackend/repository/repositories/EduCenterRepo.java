package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface EduCenterRepo extends JpaRepository<EduCenter,Long> , BaseRepository {
    Boolean existsByCenterPhoneAndIsArchived(String centerPhone, Boolean isArchived);
    Boolean existsByCeoPhoneAndIsArchived(String ceoPhone, Boolean isArchived);
    List<EduCenter> findAllByIsArchived(Boolean isArchived);
    Optional<EduCenter> findByIdAndIsArchived(Long id, Boolean isArchived);
}
