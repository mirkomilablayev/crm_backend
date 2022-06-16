package uz.crm.crmbackend.tools.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.crm.crmbackend.entity.User;
import uz.crm.crmbackend.entity.UserRole;
import uz.crm.crmbackend.repository.user.RoleRepo;
import uz.crm.crmbackend.repository.user.UserRepo;
import uz.crm.crmbackend.tools.Constant;
import uz.crm.crmbackend.tools.exceptions.UserRoleNotFoundException;

import java.util.HashSet;
import java.util.List;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;



    @Value(value = "${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {
        if (ddl.equalsIgnoreCase("create") || ddl.equalsIgnoreCase("create-drop")) {
//            User roles
            roleRepo.save(new UserRole(Constant.SUPER_ADMIN));
            roleRepo.save(new UserRole(Constant.USER));
            roleRepo.save(new UserRole(Constant.TEACHER));
            roleRepo.save(new UserRole(Constant.STUDENT));
            // this is super admin
            User admin = new User();
            admin.setFatherName("");
            admin.setPassportSerialNumber("");
            admin.setFirstName("Mirkomil");
            admin.setLastName("Ablayev");
            admin.setUserRoleSet(new HashSet<>(List.of(roleRepo.findByNameAndIsActive(Constant.SUPER_ADMIN,true).orElseThrow(() ->
                    new UserRoleNotFoundException(Constant.SUPER_ADMIN+" role not found")))));
            admin.setUsername("1");
            admin.setPassword(passwordEncoder.encode("1"));
            userRepo.save(admin);
        }
    }
}
