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
public interface PayEduRepo extends JpaRepository<PayEdu, Long>, BaseRepository {
    List<PayEdu> findAllByEduCenter_Id(Long eduCenter_id);

    Optional<PayEdu> findByEduCenter_IdAndIsActiveNow(Long eduCenter_id, Boolean isActiveNow);

    Boolean existsByEduCenter_Id(Long eduCenter_id);

    List<PayEdu> findByIsActiveNow(Boolean isActiveNow);


    @Query(value = "SELECT case when SUM(pay_amount) IS NULL then 0 else sum(pay_amount) end\n" + "           AS total\n" + "FROM pay_edu", nativeQuery = true)
    double getAllPayAmountSum();

    @Query(value = "select pe.* from pay_edu pe where pe.id = (select max(pe1.id) from pay_edu pe1 where pe1.edu_center_id = ?1)", nativeQuery = true)
    Optional<PayEdu> findLastPayment(Long eduCenterId);

}
