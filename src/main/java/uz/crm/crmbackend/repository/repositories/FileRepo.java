package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.File;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.Optional;

public interface FileRepo extends JpaRepository<File,Long>, BaseRepository {
    Optional<File> findByIdAndIsActive(Long id, Boolean isActive);
}
