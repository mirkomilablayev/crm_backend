package uz.crm.crmbackend.service.services;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.subject.SubjectCreateDto;
import uz.crm.crmbackend.dto.subject.SubjectUpdateDto;
import uz.crm.crmbackend.repository.repositories.SubjectRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;

@Service
public class SubjectService extends AbstractService<SubjectRepo> implements CrudService<SubjectCreateDto, SubjectUpdateDto>, BaseService {
    public SubjectService(SubjectRepo repository) {
        super(repository);
    }

    @Override
    public HttpEntity<?> create(SubjectCreateDto cd) {
        return null;
    }

    @Override
    public HttpEntity<?> update(SubjectUpdateDto cd) {
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
}
