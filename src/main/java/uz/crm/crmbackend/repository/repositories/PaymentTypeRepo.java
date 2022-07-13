package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.PaymentType;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentTypeRepo extends JpaRepository<PaymentType, Long> , BaseRepository {
    Boolean existsByNameAndIsActive(String name, Boolean isActive);
    Optional<PaymentType> findByIdAndIsActive(Long id, Boolean isActive);
    Boolean existsByNameAndIsActiveAndEduCenter_Id(String name, Boolean isActive, Long eduCenter_id);
    List<PaymentType> findAllByEduCenter_IdAndIsActive(Long eduCenter_id, Boolean isActive);
}
