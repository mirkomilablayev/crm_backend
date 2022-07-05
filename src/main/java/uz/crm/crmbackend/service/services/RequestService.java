package uz.crm.crmbackend.service.services;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.request.RequestCreateDto;
import uz.crm.crmbackend.dto.request.RequestUpdateDto;
import uz.crm.crmbackend.repository.repositories.RequestRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.CrudService;

@Service
public class RequestService extends AbstractService<RequestRepo> implements CrudService<RequestCreateDto, RequestUpdateDto> {
    public RequestService(RequestRepo repository) {
        super(repository);
    }

    @Override
    public HttpEntity<?> create(RequestCreateDto cd) {
        return null;
    }

    @Override
    public HttpEntity<?> update(RequestUpdateDto cd) {
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
