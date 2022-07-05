package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.PayEdu;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface PayEduRepo extends JpaRepository<PayEdu,Long> , BaseRepository {
    List<PayEdu> findAllByEduCenter_Id(Long eduCenter_id);
    Optional<PayEdu> findByEduCenter_IdAndIsActiveNow(Long eduCenter_id, Boolean isActiveNow);
}
