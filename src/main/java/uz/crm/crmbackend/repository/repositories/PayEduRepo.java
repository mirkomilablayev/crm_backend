package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import uz.crm.crmbackend.entity.PayEdu;
import uz.crm.crmbackend.repository.BaseRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface PayEduRepo extends JpaRepository<PayEdu,Long> , BaseRepository {
    List<PayEdu> findAllByEduCenter_Id(Long eduCenter_id);
    Optional<PayEdu> findByEduCenter_IdAndIsActiveNow(Long eduCenter_id, Boolean isActiveNow);
    Boolean existsByEduCenter_Id(Long eduCenter_id);
    List<PayEdu> findByIsActiveNow(Boolean isActiveNow);


    @Modifying
    @Query(nativeQuery = true, value = "select sum(pay_amount) from pay_edu")
    Double sumAllPayments();
}
