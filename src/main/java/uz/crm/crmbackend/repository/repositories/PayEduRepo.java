package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.PayEdu;
import uz.crm.crmbackend.repository.BaseRepository;

public interface PayEduRepo extends JpaRepository<PayEdu,Long> , BaseRepository {
}
