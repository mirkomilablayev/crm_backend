package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface EduCenterRepo extends JpaRepository<EduCenter,Long> , BaseRepository {
    Boolean existsByCenterPhoneAndIsArchived(String centerPhone, Boolean isArchived);
    Boolean existsByCeoPhoneAndIsArchived(String ceoPhone, Boolean isArchived);
    List<EduCenter> findAllByIsArchived(Boolean isArchived);
    Optional<EduCenter> findByIdAndIsArchived(Long id, Boolean isArchived);
    Boolean existsByIdAndIsArchived(Long id, Boolean isArchived);

    @Query(value = "select ec.*\n" +
            "from edu_center ec\n" +
            "where ec.edu_center_name like concat('%', concat(?1, '%'))\n" +
            "  and ec.center_stir like concat('%', concat(?2, '%'))\n" +
            "  and ec.ceo_full_name like concat('%', concat(?3, '%'))\n" +
            "limit ?4 offset ?5", nativeQuery = true)
    List<EduCenter> getEduCentersByPageable(
            String centerName,
            String stir,
            String ceo_name,
            Long size,
            Long page
    );
}
