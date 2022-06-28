package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.List;

public interface EduCenterRepo extends JpaRepository<EduCenter,Long> , BaseRepository {
    Boolean existsByCenterPhoneOrCeoPhoneAndIsArchived(String centerPhone, String ceoPhone, Boolean isArchived);
    List<EduCenter> findAllByIsArchived(Boolean isArchived);
}
