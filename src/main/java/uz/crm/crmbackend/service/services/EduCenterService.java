package uz.crm.crmbackend.service.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.crm.crmbackend.dto.eduCenter.*;
import uz.crm.crmbackend.entity.*;
import uz.crm.crmbackend.exceptions.ConflictException;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.exceptions.UsernameAlreadyRegisterException;
import uz.crm.crmbackend.repository.repositories.*;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;
import uz.crm.crmbackend.tools.Constant;
import uz.crm.crmbackend.tools.Util;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EduCenterService extends AbstractService<EduCenterRepo> implements BaseService, CrudService<EduCenCreateDto, EduCenUpdateDto> {

    private final CenterStatusRepo centerStatusRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final FileRepo fileRepo;
    private final RoleRepo roleRepo;
    private final Util util;


    public EduCenterService(Util util, RoleRepo roleRepo, FileRepo fileRepo, UserRepo userRepo, PasswordEncoder passwordEncoder, EduCenterRepo repository, CenterStatusRepo centerStatusRepo) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.centerStatusRepo = centerStatusRepo;
        this.fileRepo = fileRepo;
        this.roleRepo = roleRepo;
        this.util = util;
    }


    @Override
    public HttpEntity<?> create(EduCenCreateDto cd) {
        try {
            EduCenter eduCenter = new EduCenter();
            eduCenter.setCenterPhone(cd.getCenterPhone());
            eduCenter.setCenterStir(cd.getCenterStir());
            eduCenter.setCenterStatus(centerStatusRepo.findByName(Constant.status2).orElseThrow(() -> new ResourceNotFoundException("")));
            eduCenter.setEdu_centerName(cd.getEdu_centerName());
            eduCenter.setCeoPhone(cd.getCeoPhone());
            eduCenter.setCeo_full_name(cd.getCeo_full_name());
            eduCenter.setIsArchived(false);
            eduCenter.setAddedAt(LocalDateTime.now());
            eduCenter.setIsArchived(false);
            if (cd.getLogoId() != null && cd.getLogoId() > 0) {
                eduCenter.setLogoFile(fileRepo.findByIdAndIsActive(cd.getLogoId(), true).orElseThrow(ResourceNotFoundException::new));
            }

            if (userRepo.existsByUsernameAndIsDeleted(cd.getAdminUsername(), false)) {
                throw new UsernameAlreadyRegisterException("s");
            }
            EduCenter save = repository.save(eduCenter);

            User user = new User();
            user.setFullName(cd.getAdminFullName());
            user.setUsername(cd.getAdminUsername());
            user.setEduCenter(save);
            Set<UserRole> roles = new HashSet<>();
            roles.add(roleRepo.findByNameAndIsActive(Constant.ADMIN, true).orElseThrow(ResourceNotFoundException::new));
            user.setUserRoleSet(roles);
            user.setPassword(passwordEncoder.encode(cd.getAdminPassword()));
            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("");
        }
    }

    @Override
    public HttpEntity<?> update(EduCenUpdateDto cd) {
        EduCenter eduCenter = repository.findByIdAndIsArchived(cd.getEduCenterId(), false).orElseThrow(() -> new ResourceNotFoundException(""));

        if (cd.getEdu_centerName() != null
                && util.checkBlank(cd.getEdu_centerName())
                && !eduCenter.getEdu_centerName().equals(cd.getEdu_centerName())) {
            eduCenter.setEdu_centerName(cd.getEdu_centerName());
        }

        if (cd.getCenterPhone() != null
                && util.checkBlank(cd.getCenterPhone())
                && !eduCenter.getCenterPhone().equals(cd.getCenterPhone())) {
            eduCenter.setCenterPhone(cd.getCenterPhone());
        }

        if (cd.getCenterStir() != null
                && util.checkBlank(cd.getCenterStir())
                && !eduCenter.getCenterStir().equals(cd.getCenterStir())) {
            eduCenter.setCenterStir(cd.getCenterStir());
        }

        if (cd.getCeo_full_name() != null
                && util.checkBlank(cd.getCeo_full_name())
                && !eduCenter.getCeo_full_name().equals(cd.getCeo_full_name())) {
            eduCenter.setCeo_full_name(cd.getCeo_full_name());
        }

        if (cd.getCeoPhone() != null
                && util.checkBlank(cd.getCeoPhone())
                && !eduCenter.getCeoPhone().equals(cd.getCeoPhone())) {
            eduCenter.setCeoPhone(cd.getCeoPhone());
        }

        if (cd.getCenterStatusName() != null
                && util.checkBlank(cd.getCenterStatusName())
                && !eduCenter.getCenterStatus().getName().equals(cd.getCenterStatusName())) {
            eduCenter.setCenterStatus(centerStatusRepo.findByName(cd.getCenterStatusName())
                    .orElseThrow(ResourceNotFoundException::new));
        }

        if (cd.getLogoId() != null && cd.getLogoId() > 0) {
            eduCenter.setLogoFile(fileRepo.findByIdAndIsActive(cd.getLogoId(), true)
                    .orElseThrow(ResourceNotFoundException::new));
        }
        return null;
    }

    @Override
    public HttpEntity<?> get(Long id) {
        Optional<EduCenter> eduCenterOptional = repository.findByIdAndIsArchived(id, false);
        if (eduCenterOptional.isPresent()) {
            EduCenterShowDto eduCenterShowDto = new EduCenterShowDto();
            EduCenter eduCenter = eduCenterOptional.get();
            eduCenterShowDto.setId(eduCenter.getId());
            eduCenterShowDto.setEduCenterName(eduCenter.getEdu_centerName());
            eduCenterShowDto.setStatus(eduCenter.getCenterStatus().getName());
            eduCenterShowDto.setPhoneNumber(eduCenter.getCenterPhone());
            if (eduCenter.getLogoFile() != null && eduCenter.getLogoFile().getId() > 0)
                eduCenterShowDto.setLogoId(eduCenter.getLogoFile().getId());
            return ResponseEntity.status(HttpStatus.OK).body(eduCenterShowDto);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("EduCenter not found");
        }
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        Optional<EduCenter> eduCenterOptional = repository.findByIdAndIsArchived(id, false);
        if (eduCenterOptional.isPresent()) {
            EduCenter eduCenter = eduCenterOptional.get();
            eduCenter.setIsArchived(true);
            User user = new User();
            for (User user1 : userRepo.findAllByEduCenter_IdAndIsDeleted(eduCenter.getId(), false)) {
                boolean flag = false;
                for (UserRole userRole : user1.getUserRoleSet()) {
                    if (userRole.getName().equals(Constant.ADMIN)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    user = user1;
                    break;
                }
            }
            user.setIsDeleted(true);
            userRepo.save(user);
            repository.save(eduCenter);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Edu center not found");
        }
    }

    public HttpEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(getAllEduCenter(repository.findAllByIsArchived(false)));
    }

    public List<EduCenterShowDto> getAllEduCenter(List<EduCenter> eduCenters) {
        List<EduCenterShowDto> eduCenterShowDtos = new ArrayList<>();
        eduCenters.forEach(a -> {
            EduCenterShowDto item = new EduCenterShowDto();
            item.setId(a.getId());
            User user;
            try {
                user = getAdministrator(a.getId());
            } catch (NullPointerException e) {
                throw new ConflictException("");
            }
            assert user != null;
            item.setAdminId(user.getId());
            item.setAdminName(user.getFullName());
            item.setUsername(user.getUsername());
            item.setPassword(user.getPass());
            item.setEduCenterName(a.getEdu_centerName());
            if (a.getCenterStatus() != null)
                item.setStatus(a.getCenterStatus().getName());
            item.setCeo(a.getCeo_full_name());
            item.setPhoneNumber(a.getCenterPhone());
            item.setJoiningAt(timeFormatter(a.getAddedAt()));

            if (a.getLogoFile() != null)
                item.setLogoId(a.getLogoFile().getId());
            eduCenterShowDtos.add(item);
        });
        return eduCenterShowDtos;
    }

    private User getAdministrator(Long id) {
        for (User user : userRepo.findAllByEduCenter_IdAndIsDeleted(id, false)) {
            boolean flag = false;
            for (UserRole userRole : user.getUserRoleSet()) {
                if (userRole.getName().equals(Constant.ADMIN)) {
                    flag = true;
                    break;
                }
            }
            if (flag)
                return user;
        }
        return null;
    }

    private String timeFormatter(LocalDateTime addedAt) {
        if (addedAt != null) {
            return "" + addedAt.getDayOfMonth() + "-" + addedAt.getMonth() + ", " + addedAt.getYear() + " " + addedAt.getHour() + ":" + addedAt.getMinute() + ":" + addedAt.getSecond();
        } else {
            return "";
        }
    }

    public HttpEntity<?> getAllStatus() {
        return ResponseEntity.status(HttpStatus.OK).body(centerStatusRepo.findAll());
    }

    public HttpEntity<?> getArchived(Long id) {
        Optional<EduCenter> eduCenterOptional = repository.findByIdAndIsArchived(id, true);
        if (eduCenterOptional.isPresent()) {
            EduCenter eduCenter = eduCenterOptional.get();
            eduCenter.setIsArchived(true);
            repository.save(eduCenter);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Edu center not found");
        }
    }

    public HttpEntity<?> getAllArchived() {
        return ResponseEntity.status(HttpStatus.OK).body(getAllEduCenter(repository.findAllByIsArchived(true)));
    }

    public HttpEntity<?> saveFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            File newFile = new File();
            newFile.setContent_type(file.getContentType());
            String originalFilename = file.getOriginalFilename();
            newFile.setOriginalName(originalFilename);
            assert originalFilename != null;
            String[] split = originalFilename.split("\\.");
            String generatedName = UUID.randomUUID() + "." + split[split.length - 1];
            String filePath = Constant.filePaths + "/" + generatedName;
            newFile.setGeneratedName(generatedName);
            newFile.setFile_path(filePath);
            File save = fileRepo.save(newFile);
            Path path = Paths.get(filePath);
            try {
                Files.copy(file.getInputStream(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.status(HttpStatus.OK).body(save.getId());
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong");
    }

    public void showPictures(Long id, HttpServletResponse response) {
        Optional<File> byId = fileRepo.findByIdAndIsActive(id, true);
        if (byId.isPresent()) {
            File file = byId.get();
            try {
                response.setHeader("Content-Disposition", file.getContent_type());
                response.setContentType(file.getContent_type());
                FileInputStream inputStream = new FileInputStream(file.getFile_path());
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            } catch (IOException ignored) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public HttpEntity<?> getEduCenterName() {
        List<EduCenterSelectInfo> res = new ArrayList<>();
        repository.findAllByIsArchived(false).forEach(a -> {
            EduCenterSelectInfo b = new EduCenterSelectInfo();
            b.setEduCenterId(a.getId());
            b.setEduCenterName(a.getEdu_centerName());
            res.add(b);
        });
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    public HttpEntity<?> restoreEduCenter(Long eduCenterId) {
        EduCenter eduCenter = repository.findByIdAndIsArchived(eduCenterId, true).orElseThrow(ResourceNotFoundException::new);
        eduCenter.setIsArchived(false);
        repository.save(eduCenter);
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


    public HttpEntity<?> changeStatus(Long eduCenterId) {
        EduCenter eduCenter = repository.findByIdAndIsArchived(eduCenterId, false).orElseThrow(ResourceNotFoundException::new);
        CenterStatus centerStatus = eduCenter.getCenterStatus();
        if (centerStatus.getName().equals("Active")) {
            eduCenter.setCenterStatus(centerStatusRepo.findByName("UnActive").orElseThrow(ResourceNotFoundException::new));
            repository.save(eduCenter);
        } else if (centerStatus.getName().equals("UnActive")) {
            eduCenter.setCenterStatus(centerStatusRepo.findByName("Active").orElseThrow(ResourceNotFoundException::new));
            repository.save(eduCenter);
        } else {
            throw new ConflictException("");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }


    public HttpEntity<?> deleteMultiple(List<Long> ids) {
        ids.forEach(id -> {
            Optional<EduCenter> eduCenterOptional = repository.findByIdAndIsArchived(id, false);
            if (eduCenterOptional.isPresent()) {
                EduCenter eduCenter = eduCenterOptional.get();
                eduCenter.setIsArchived(true);
                repository.save(eduCenter);
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

}
