package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.Room;
import uz.crm.crmbackend.repository.BaseRepository;

public interface RoomRepo extends JpaRepository<Room,Long>, BaseRepository {

    Boolean existsByNameAndEduCenter_Id(String name, Long eduCenter_id);

}
