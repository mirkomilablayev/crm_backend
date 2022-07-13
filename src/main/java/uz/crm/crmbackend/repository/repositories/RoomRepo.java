package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.Room;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepo extends JpaRepository<Room,Long>, BaseRepository {

    Boolean existsByNameAndEduCenter_Id(String name, Long eduCenter_id);
    Optional<Room> findByIdAndIsActive(Long id, Boolean isActive);
    List<Room> findAllByEduCenter_IdAndIsActive(Long eduCenter_id, Boolean isActive);

}
