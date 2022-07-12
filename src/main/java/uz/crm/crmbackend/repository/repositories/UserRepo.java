package uz.crm.crmbackend.repository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.entity.User;
import uz.crm.crmbackend.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long>, BaseRepository {
    Optional<User> findByUsernameAndIsDeleted(String username, Boolean isDeleted);

    Boolean existsByUsernameAndIsDeleted(String username, Boolean isDeleted);

    Optional<User> findByIdAndIsDeleted(Long id, Boolean isDeleted);

    Boolean existsByPhoneNumberAndIsDeleted(String phoneNumber, Boolean isDeleted);

    @Query(value = "select u.*\n" +
            "from users u\n" +
            "         join users_edu_center uec on u.id = uec.users_id\n" +
            "         join users_user_role_set uurs on u.id = uurs.users_id\n" +
            "         join user_roles ur on ur.id = uurs.user_role_set_id\n" +
            "where u.is_deleted = false\n" +
            "  and uec.edu_center_id = ?\n" +
            "  and (select t.id from user_roles t where t.name = 'ADMINISTRATOR') in (uurs.user_role_set_id)", nativeQuery = true)
    Optional<User> getUser(Long eduCenterId);
}
