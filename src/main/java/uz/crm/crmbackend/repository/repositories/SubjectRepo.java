package uz.crm.crmbackend.repository.repositories;

import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.Subject;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;


public interface SubjectRepo extends JpaRepository<Subject,Long>, BaseRepository {
    boolean existsByNameAndEduCenter_IdAndIsActive(String name, Long eduCenter_id, Boolean isActive);
    Optional<Subject> findByIdAndIsActive(Long id, Boolean isActive);
    List<Subject> findAllByEduCenter_IdAndIsActive(Long eduCenter_id, Boolean isActive);

    Optional<Subject> findByIdAndIsDeleted(Long id, Boolean isDeleted);
    List<Subject> findAllByEduCenter_IdAndIsDeleted(Long eduCenter_id, Boolean isDeleted);
}
