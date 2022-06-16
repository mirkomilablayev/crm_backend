package uz.crm.crmbackend.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.UserRole;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<UserRole,Long>, BaseRepository {
Optional<UserRole> findByNameAndIsActive(String name, Boolean isActive);
}
