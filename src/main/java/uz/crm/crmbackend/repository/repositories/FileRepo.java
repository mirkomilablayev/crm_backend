package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.File;
import uz.crm.crmbackend.repository.BaseRepository;

public interface FileRepo extends JpaRepository<File,Long>, BaseRepository {
}
