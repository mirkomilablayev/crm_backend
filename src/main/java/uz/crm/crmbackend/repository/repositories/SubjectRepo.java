package uz.crm.crmbackend.repository.repositories;

import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.Subject;
import uz.crm.crmbackend.repository.BaseRepository;


public interface SubjectRepo extends JpaRepository<Subject,Long>, BaseRepository {
}
