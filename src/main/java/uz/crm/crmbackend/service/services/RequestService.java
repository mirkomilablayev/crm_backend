package uz.crm.crmbackend.service.services;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.request.RequestCreateDto;
import uz.crm.crmbackend.dto.request.RequestDto;
import uz.crm.crmbackend.dto.request.RequestShowDto;
import uz.crm.crmbackend.dto.request.RequestUpdateDto;
import uz.crm.crmbackend.entity.RequestToGetDemo;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.repository.repositories.RequestRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.CrudService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        RequestToGetDemo b = repository.findByIdAndIsActive(id, true).orElseThrow(ResourceNotFoundException::new);
        RequestShowDto a = new RequestShowDto();
        a.setSentAt(b.getSentAt());
        a.setPhoneNumber(b.getPhoneNumber());
        a.setEduCenterName(b.getEduCenterName());
        a.setFullName(b.getFullName());
        a.setId(b.getId());
        b.setIsSeen(true);
        repository.save(b);
        return ResponseEntity.status(HttpStatus.OK).body(a);
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

    private List<RequestDto> getRequestDto(List<RequestToGetDemo> allByIsActive) {
        List<RequestDto> item = new ArrayList<>();
        allByIsActive.forEach(a -> {
            RequestDto b = new RequestDto();
            b.setFullName(a.getFullName());
            b.setSentAt(a.getSentAt());
            b.setId(a.getId());
            b.setIsSeen(a.getIsSeen());
            item.add(b);
        });
        return item;
    }
}
