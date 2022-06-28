package uz.crm.crmbackend.controller.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.controller.CrudController;
import uz.crm.crmbackend.dto.eduCenter.EduCenCreateDto;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.service.services.EduCenterService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/eduCenter")
public class EduCenterController extends AbstractController<EduCenterService> implements CrudController<EduCenCreateDto, EduCenCreateDto> {


    public EduCenterController(EduCenterService service) {
        super(service);
    }


    @PostMapping("/createEduCenter")
    @Override
    public HttpEntity<?> create(EduCenCreateDto cd) {
        return service.create(cd);
    }

    @Override
    public HttpEntity<?> update(EduCenCreateDto cd) {
        return null;
    }


    @Override
    public HttpEntity<?> get(Long id) {
        return null;
    }

    @DeleteMapping("delete/{id}")
    @Override
    public HttpEntity<?> deleteById(Long id) {
        return null;
    }


    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){
        return service.getAll();
    }
}
