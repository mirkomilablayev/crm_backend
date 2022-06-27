package uz.crm.crmbackend.service.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.eduCenter.EduCenDto;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.repository.repositories.EduCenterRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;

import java.time.LocalDateTime;

@Service
public class EduCenterService extends AbstractService<EduCenterRepo> implements BaseService, CrudService<EduCenter, EduCenDto> {
    public EduCenterService(EduCenterRepo repository) {
        super(repository);
    }



    @Override
    public HttpEntity<?> create(EduCenter cd) {
        cd.setAddedAt(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.OK).body(cd);
    }

    @Override
    public HttpEntity<?> update(EduCenDto cd) {
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
