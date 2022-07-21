package uz.crm.crmbackend.service.services;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.subject.SubjectChangeStatusDto;
import uz.crm.crmbackend.dto.subject.SubjectCreateDto;
import uz.crm.crmbackend.dto.subject.SubjectShowDto;
import uz.crm.crmbackend.dto.subject.SubjectUpdateDto;
import uz.crm.crmbackend.entity.Subject;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.repository.repositories.SubjectRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;
import uz.crm.crmbackend.tools.Constant;
import uz.crm.crmbackend.tools.Util;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService extends AbstractService<SubjectRepo> implements CrudService<SubjectCreateDto, SubjectUpdateDto>, BaseService {
    private final Util util;

    public SubjectService(SubjectRepo repository, Util util) {
        super(repository);
        this.util = util;
    }

    @Override
    public HttpEntity<?> create(SubjectCreateDto cd) {
        Long eduCenterId = util.getEduCenterId();
        if (!repository.existsByNameAndEduCenter_IdAndIsActive(cd.getSubjectName(), eduCenterId, true)) {
            Subject subject = new Subject();
            subject.setComment(cd.getComment());
            subject.setName(cd.getSubjectName());
            subject.setIsActive(true);
            subject.setEduCenter(util.getCurrentUser().getEduCenter());
            Subject save = repository.save(subject);
            return ResponseEntity.status(HttpStatus.OK).body(save);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(cd.getSubjectName() + " is already exist");
        }
    }

    @Override
    public HttpEntity<?> update(SubjectUpdateDto cd) {
        Subject subject = repository.findByIdAndIsActive(cd.getId(), true).orElseThrow(ResourceNotFoundException::new);

        if (!subject.getComment().equals(cd.getComment())) {
            subject.setComment(cd.getComment());
        }

        if (!subject.getName().equals(cd.getName())) {
            subject.setName(cd.getName());
        }
        Subject save = repository.save(subject);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

    @Override
    public HttpEntity<?> get(Long id) {
        Subject subject = repository.findByIdAndIsActive(id, true).orElseThrow(ResourceNotFoundException::new);
        SubjectShowDto subjectShowDto = new SubjectShowDto();
        subjectShowDto.setComment(subjectShowDto.getComment());
        subjectShowDto.setName(subject.getName());
        subjectShowDto.setId(subjectShowDto.getId());
        return ResponseEntity.status(HttpStatus.OK).body(subjectShowDto);
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        Subject subject = repository.findByIdAndIsActive(id, true).orElseThrow(ResourceNotFoundException::new);
        subject.setIsActive(false);
        repository.save(subject);
        return ResponseEntity.status(HttpStatus.OK).body("Mufoffaqqiyatli o'chirildi");
    }

    public HttpEntity<?> getAllActive() {
        List<SubjectShowDto> res = new ArrayList<>();
        Long eduCenterId = util.getEduCenterId();
        repository.findAllByEduCenter_IdAndIsDeleted(eduCenterId, false).forEach(subject -> {
                SubjectShowDto subjectShowDto = new SubjectShowDto();
                subjectShowDto.setId(subject.getId());
                subjectShowDto.setName(subject.getName());
                subjectShowDto.setComment(subject.getComment());
                res.add(subjectShowDto);
        });
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    public HttpEntity<?> changeStatus(SubjectChangeStatusDto dto) {
        Subject subject = repository.findByIdAndIsActive(dto.getSubjectId(), false).orElseThrow(ResourceNotFoundException::new);
        if (subject.getIsActive()){
            subject.setIsActive(false);
            repository.save(subject);
        }else{
            subject.setIsActive(true);
            repository.save(subject);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Status is successfully changed");
    }
}
