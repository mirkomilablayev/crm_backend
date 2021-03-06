package uz.crm.crmbackend.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.user.RegisterDto;
import uz.crm.crmbackend.dto.user.RegisterStudent;
import uz.crm.crmbackend.entity.User;
import uz.crm.crmbackend.entity.UserRole;
import uz.crm.crmbackend.repository.repositories.EduCenterRepo;
import uz.crm.crmbackend.repository.repositories.RoleRepo;
import uz.crm.crmbackend.repository.repositories.UserRepo;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.exceptions.UserRoleNotFoundException;
import uz.crm.crmbackend.exceptions.UsernameAlreadyRegisterException;
import uz.crm.crmbackend.tools.Constant;
import uz.crm.crmbackend.tools.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService , BaseService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final EduCenterRepo eduCenterRepo;
    private final Util util;


    public HttpEntity<?> registerA(RegisterDto cd) {
        if (!userRepo.existsByUsernameAndIsDeleted(cd.getUsername(),false)) {
            User user = new User();
            user.setPassword(passwordEncoder.encode(cd.getPassword()));
            user.setPass(cd.getPassword());
            String user_role = Constant.ADMIN;
            Set<UserRole> userRoles = new HashSet<>();
            UserRole userRole = roleRepo.findByNameAndIsActive(user_role,true).orElseThrow(() -> new UserRoleNotFoundException(user_role + " not found"));
            userRoles.add(userRole);
            user.setUserRoleSet(userRoles);
            user.setPhoneNumber(cd.getPhoneNumber());
            user.setUsername(cd.getUsername());
            user.setFullName(cd.getFullName());
            user.setUserRoleSet(userRoles);
            return ResponseEntity.status(HttpStatus.OK).body(userRepo.save(user));
        }
        throw new UsernameAlreadyRegisterException(cd.getUsername()+ " is already registered");
    }

    public HttpEntity<?> registerT(RegisterDto registerDto) {
        if (!userRepo.existsByUsernameAndIsDeleted(registerDto.getUsername(),false)) {
            User user = new User();
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setPass(registerDto.getPassword());
            String user_role = Constant.TEACHER;
            Set<UserRole> userRoles = new HashSet<>();
            UserRole userRole = roleRepo.findByNameAndIsActive(user_role,true).orElseThrow(() -> new UserRoleNotFoundException(user_role + " not found"));
            userRoles.add(userRole);
            user.setUserRoleSet(userRoles);
            user.setUsername(registerDto.getUsername());
            user.setFullName(registerDto.getFullName());
            user.setUserRoleSet(userRoles);
            return ResponseEntity.status(HttpStatus.OK).body(userRepo.save(user));
        }
        throw new UsernameAlreadyRegisterException(registerDto.getUsername()+ " is already registered");
    }

    public HttpEntity<?> registerS(RegisterStudent registerStudent) {
        if (userRepo.existsByUsernameAndIsDeleted(registerStudent.getUsername(), false)){
            User user = new User();
            user.setFullName(registerStudent.getFullName());
            user.setPassword(passwordEncoder.encode(registerStudent.getPassword()));
            user.setPass(registerStudent.getPassword());
            user.setPhoneNumber(registerStudent.getPersonalPhoneNumber());
            user.setRelativesPhoneNumber(registerStudent.getRelativesPhoneNumber());
            user.setUserRoleSet(new HashSet<>(List.of(
                    roleRepo.findByNameAndIsActive(Constant.TEACHER,true).orElseThrow(ResourceNotFoundException::new)
            )));
            user.setCreatedAt(registerStudent.getBirthDate());
            user.setEduCenter(eduCenterRepo.findByIdAndIsArchived(util.getEduCenterId(), false).orElseThrow(ResourceNotFoundException::new));
            user.setIsDeleted(false);
            userRepo.save(user);
        }{
            throw new UsernameAlreadyRegisterException("");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return   userRepo.findByUsernameAndIsDeleted(username,false).orElseThrow(() -> new ResourceNotFoundException(""));
    }
}
