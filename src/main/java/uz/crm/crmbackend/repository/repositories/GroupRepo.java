package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.Group;
import uz.crm.crmbackend.repository.BaseRepository;

public interface GroupRepo extends JpaRepository<Group,Long>, BaseRepository {

}
