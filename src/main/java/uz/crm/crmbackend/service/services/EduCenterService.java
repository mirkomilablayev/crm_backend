package uz.crm.crmbackend.service.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.crm.crmbackend.dto.eduCenter.EduCenCreateDto;
import uz.crm.crmbackend.dto.eduCenter.EduCenterShowDto;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.entity.File;
import uz.crm.crmbackend.entity.User;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.repository.repositories.CenterStatusRepo;
import uz.crm.crmbackend.repository.repositories.EduCenterRepo;
import uz.crm.crmbackend.repository.repositories.FileRepo;
import uz.crm.crmbackend.repository.repositories.UserRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;
import uz.crm.crmbackend.tools.Constant;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class EduCenterService extends AbstractService<EduCenterRepo> implements BaseService, CrudService<EduCenCreateDto, EduCenCreateDto> {

    private final CenterStatusRepo centerStatusRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final FileRepo fileRepo;

    public EduCenterService(FileRepo fileRepo, UserRepo userRepo, PasswordEncoder passwordEncoder, EduCenterRepo repository, CenterStatusRepo centerStatusRepo) {
        super(repository);
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.centerStatusRepo = centerStatusRepo;
        this.fileRepo = fileRepo;
    }


    @Override
    public HttpEntity<?> create(EduCenCreateDto cd) {
        // TODO: 6/28/2022 hamma telefon raqamlarni tekshirish kerak
        if (!repository.existsByCenterPhoneOrCeoPhoneAndIsArchived(cd.getCenterPhone(), cd.getCeoPhone(), false)
                && !userRepo.existsByUsernameAndIsDeleted(cd.getAdminUsername(), false)) {
            try {
                EduCenter eduCenter = new EduCenter();
                eduCenter.setCenterPhone(cd.getCenterPhone());
                eduCenter.setCenterStir(cd.getCenterStir());
                eduCenter.setCenterStatus(centerStatusRepo.findByName(cd.getCenterStatusName()).orElseThrow(() -> new ResourceNotFoundException("")));
                eduCenter.setEdu_centerName(cd.getEdu_centerName());
                eduCenter.setCeoPhone(cd.getCeoPhone());
                eduCenter.setCeo_full_name(cd.getCeo_full_name());
                eduCenter.setIsArchived(false);
                eduCenter.setAddedAt(LocalDateTime.now());
                eduCenter.setIsArchived(false);
                eduCenter.setStartTime(cd.getJoiningStart());
                eduCenter.setEndTime(cd.getJoiningEnd());
                if (cd.getLogoId() != null) {
                    eduCenter.setLogoFile(fileRepo.findByIdAndIsActive(cd.getLogoId(), true).orElseThrow(ResourceNotFoundException::new));
                }
                repository.save(eduCenter);

                User user = new User();
                user.setFullName(cd.getAdminFullName());
                user.setUsername(cd.getAdminUsername());
                user.setPassword(passwordEncoder.encode(cd.getAdminPassword()));
                userRepo.save(user);
                return ResponseEntity.status(HttpStatus.OK).body("Success");
            } catch (NullPointerException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("");
            }
        } else
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Center or Ceo Phone number is already exist");
    }

    @Override
    public HttpEntity<?> update(EduCenCreateDto cd) {
        return null;
    }

    @Override
    public HttpEntity<?> get(Long id) {
        Optional<EduCenter> eduCenterOptional = repository.findByIdAndIsArchived(id, false);
        if (eduCenterOptional.isPresent()){
            EduCenterShowDto eduCenterShowDto = new EduCenterShowDto();
            EduCenter eduCenter = eduCenterOptional.get();
            eduCenterShowDto.setId(eduCenter.getId());
            eduCenterShowDto.setEduCenterName(eduCenter.getEdu_centerName());
            eduCenterShowDto.setStatus(eduCenter.getCenterStatus().getName());
            eduCenterShowDto.setJoiningAt(eduCenter.getStartTime());
            eduCenterShowDto.setPhoneNumber(eduCenter.getCenterPhone());
            eduCenterShowDto.setLogoId(eduCenter.getLogoFile().getId());
            return ResponseEntity.status(HttpStatus.OK).body(eduCenterShowDto);
        }else{
        return ResponseEntity.status(HttpStatus.OK).body("EduCenter not found");
        }
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        Optional<EduCenter> eduCenterOptional = repository.findByIdAndIsArchived(id, false);
        if (eduCenterOptional.isPresent()) {
            EduCenter eduCenter = eduCenterOptional.get();
            eduCenter.setIsArchived(true);
            repository.save(eduCenter);
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Edu center not found");
        }
    }

    public HttpEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(getAllEduCenter(repository.findAllByIsArchived(false)));
    }

    public List<EduCenterShowDto> getAllEduCenter(List<EduCenter> eduCenters){
        List<EduCenterShowDto> eduCenterShowDtos = new ArrayList<>();
        eduCenters.forEach(a -> {
            EduCenterShowDto item = new EduCenterShowDto();
            item.setId(a.getId());
            item.setEduCenterName(a.getEdu_centerName());
            item.setStatus(a.getCenterStatus().getName());
            item.setCeo(a.getCeo_full_name());
            item.setPhoneNumber(a.getCenterPhone());
            item.setJoiningAt(a.getStartTime());
            item.setLogoId(a.getLogoFile().getId());
            eduCenterShowDtos.add(item);
        });
        return eduCenterShowDtos;
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
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Edu center not found");
        }
    }

    public HttpEntity<?> getAllArchived() {
        return ResponseEntity.status(HttpStatus.OK).body(getAllEduCenter(repository.findAllByIsArchived(true)));
    }

    public HttpEntity<?> saveFile(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null){
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
        Optional<File> byId = fileRepo.findByIdAndIsActive(id,true);
        if (byId.isPresent()){
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
}
