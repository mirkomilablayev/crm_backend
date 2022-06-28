package uz.crm.crmbackend.service.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.eduCenter.EduCenCreateDto;
import uz.crm.crmbackend.entity.CenterStatus;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.entity.User;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.repository.repositories.CenterStatusRepo;
import uz.crm.crmbackend.repository.repositories.EduCenterRepo;
import uz.crm.crmbackend.repository.repositories.UserRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;

import java.time.LocalDateTime;

@Service
public class EduCenterService extends AbstractService<EduCenterRepo> implements BaseService, CrudService<EduCenCreateDto, EduCenCreateDto> {

    private final CenterStatusRepo centerStatusRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    public EduCenterService(UserRepo userRepo, PasswordEncoder passwordEncoder, EduCenterRepo repository, CenterStatusRepo centerStatusRepo) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.centerStatusRepo = centerStatusRepo;
    }


    @Override
    public HttpEntity<?> create(EduCenCreateDto cd) {

        if (!repository.existsByCenterPhoneOrCeoPhoneAndIsArchived(cd.getCenterPhone(), cd.getCeoPhone(), false)) {
            EduCenter eduCenter = new EduCenter();
            eduCenter.setCenterPhone(cd.getCenterPhone());
            eduCenter.setCenterStir(cd.getCenterStir());
            eduCenter.setCenterStatus(centerStatusRepo.findById(cd.getCenterStatusId()).orElseThrow(() -> new ResourceNotFoundException("")));
            eduCenter.setEdu_centerName(cd.getEdu_centerName());
            eduCenter.setCeoPhone(cd.getCeoPhone());
            eduCenter.setCeo_full_name(cd.getCeo_full_name());
            eduCenter.setCenterStatus(centerStatusRepo.findById(cd.getCenterStatusId()).orElseThrow(() -> new ResourceNotFoundException("")));
            eduCenter.setIsArchived(false);
            eduCenter.setAddedAt(LocalDateTime.now());
            EduCenter save = repository.save(eduCenter);

            if (userRepo.existsByUsernameAndIsDeleted(cd.getAdminUsername(), false)) {
                User user = new User();
                user.setFullName(cd.getAdminFullName());
                user.setUsername(cd.getAdminUsername());
                user.setPassword(passwordEncoder.encode(cd.getAdminPassword()));
                userRepo.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Success");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(cd.getAdminUsername() + " is already exist ");
            }
        }


        return null;
    }

    @Override
    public HttpEntity<?> update(EduCenCreateDto cd) {
        return null;
    }

    @Override
    public HttpEntity<?> get(Long id) {
        return null;
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        return null;
    }

    public HttpEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByIsArchived(false));
    }
}
