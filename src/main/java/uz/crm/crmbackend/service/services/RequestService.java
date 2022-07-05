package uz.crm.crmbackend.service.services;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.request.RequestCreateDto;
import uz.crm.crmbackend.dto.request.RequestShowDto;
import uz.crm.crmbackend.dto.request.RequestUpdateDto;
import uz.crm.crmbackend.entity.RequestToGetDemo;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.repository.repositories.RequestRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.CrudService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RequestService extends AbstractService<RequestRepo> implements CrudService<RequestCreateDto, RequestUpdateDto> {
    public RequestService(RequestRepo repository) {
        super(repository);
    }

    @Override
    public HttpEntity<?> create(RequestCreateDto cd) {
        RequestToGetDemo requestToGetDemo = new RequestToGetDemo();
        requestToGetDemo.setFullName(cd.getFullName());
        requestToGetDemo.setPhoneNumber(cd.getPhoneNumber());
        requestToGetDemo.setEduCenterName(cd.getEduCenterName());
        requestToGetDemo.setSentAt(LocalDateTime.now());
        RequestToGetDemo save = repository.save(requestToGetDemo);
        return ResponseEntity.status(HttpStatus.OK).body(save);
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
        RequestToGetDemo request = repository.findByIdAndIsActive(id, true).orElseThrow(ResourceNotFoundException::new);
        request.setIsActive(false);
        RequestToGetDemo save = repository.save(request);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

    public HttpEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(getRequestDto(repository.findAllByIsActive(true)));
    }

    private List<RequestShowDto> getRequestDto(List<RequestToGetDemo> allByIsActive) {


        return null;
    }
}
