package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.RequestToGetDemo;
import uz.crm.crmbackend.repository.BaseRepository;

public interface RequestRepo extends JpaRepository<RequestToGetDemo,Long>, BaseRepository {

}
