package uz.crm.crmbackend.controller.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.dto.request.RequestCreateDto;
import uz.crm.crmbackend.dto.request.RequestUpdateDto;
import uz.crm.crmbackend.service.CrudService;
import uz.crm.crmbackend.service.services.RequestService;

@RestController
@RequestMapping("/api/request")
public class RequestController extends AbstractController<RequestService> implements CrudService<RequestCreateDto, RequestUpdateDto> {
    public RequestController(RequestService service) {
        super(service);
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
