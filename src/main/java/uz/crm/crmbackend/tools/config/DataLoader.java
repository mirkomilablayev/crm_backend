package uz.crm.crmbackend.tools.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.crm.crmbackend.entity.user.User;
import uz.crm.crmbackend.entity.user.UserRole;
import uz.crm.crmbackend.repository.user.RoleRepo;
import uz.crm.crmbackend.repository.user.UserRepo;
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
            roleRepo.save(new UserRole("USER"));
            roleRepo.save(new UserRole("MANAGER"));
            roleRepo.save(new UserRole("ADMIN"));
            // this is super admin
            User admin = new User();
            admin.setFirstName("Mirkomil");
            admin.setLastName("Ablayev");
            admin.setUserRoleSet(new HashSet<>(List.of(roleRepo.findByNameAndIsActive("ADMIN",true).orElseThrow(() -> new UserRoleNotFoundException("ADMIN role not found")))));
            admin.setUsername("1");
            admin.setPassword(passwordEncoder.encode("1"));
            userRepo.save(admin);
        }
    }
}
