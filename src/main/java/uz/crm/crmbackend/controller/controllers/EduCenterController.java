package uz.crm.crmbackend.controller.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.controller.CrudController;
import uz.crm.crmbackend.dto.eduCenter.EduCenDto;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.service.services.EduCenterService;

@RestController
@RequestMapping("/api/eduCenter")
public class EduCenterController extends AbstractController<EduCenterService> implements CrudController<EduCenter, EduCenDto> {


    public EduCenterController(EduCenterService service) {
        super(service);
    }

    @PostMapping("/createEduCenter")
    @Override
    public HttpEntity<?> create(EduCenter cd) {
        return service.create(cd);
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


    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){
        return service.getAll();
    }
}
