package uz.crm.crmbackend.controller.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.controller.CrudController;
import uz.crm.crmbackend.dto.subject.SubjectCreateDto;
import uz.crm.crmbackend.dto.subject.SubjectUpdateDto;
import uz.crm.crmbackend.service.services.SubjectService;

@RestController
@RequestMapping("/api/subject")
public class SubjectController extends AbstractController<SubjectService> implements CrudController<SubjectCreateDto, SubjectUpdateDto> {


    public SubjectController(SubjectService service) {
        super(service);
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
