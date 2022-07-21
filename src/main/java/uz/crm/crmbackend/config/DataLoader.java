package uz.crm.crmbackend.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.crm.crmbackend.entity.CenterStatus;
import uz.crm.crmbackend.entity.User;
import uz.crm.crmbackend.entity.UserRole;
import uz.crm.crmbackend.repository.repositories.CenterStatusRepo;
import uz.crm.crmbackend.repository.repositories.RoleRepo;
import uz.crm.crmbackend.repository.repositories.UserRepo;
import uz.crm.crmbackend.tools.Constant;
import uz.crm.crmbackend.exceptions.UserRoleNotFoundException;

import java.util.Collections;
import java.util.HashSet;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final CenterStatusRepo centerStatusRepo;



    @Value(value = "${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {
        if (ddl.equalsIgnoreCase("create") || ddl.equalsIgnoreCase("create-drop")) {
//            User roles
            roleRepo.save(new UserRole(Constant.SUPER_ADMIN));
            roleRepo.save(new UserRole(Constant.ADMIN));
            roleRepo.save(new UserRole(Constant.TEACHER));
            roleRepo.save(new UserRole(Constant.STUDENT));


            centerStatusRepo.save(new CenterStatus(Constant.status1));
            centerStatusRepo.save(new CenterStatus(Constant.status2));
            centerStatusRepo.save(new CenterStatus(Constant.status3));


            // this is super admin
            User admin = new User();
            admin.setFullName("Anonymous User");
            admin.setIsDeleted(false);
            admin.setUserRoleSet(new HashSet<>(Collections.singletonList(
                    roleRepo.findByNameAndIsActive(Constant.SUPER_ADMIN, true)
                            .orElseThrow(() ->
                                    new UserRoleNotFoundException(Constant.SUPER_ADMIN + " role not found"))
            )));
            admin.setUsername("1");
            admin.setPassword(passwordEncoder.encode("1"));
            userRepo.save(admin);

        }
    }
}
