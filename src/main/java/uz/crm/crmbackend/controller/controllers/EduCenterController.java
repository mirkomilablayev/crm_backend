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


    @GetMapping("/getOneEduCenter/{id}")
    @Override
    public HttpEntity<?> get(Long id) {
        return service.get(id);
    }

    @DeleteMapping("delete/{id}")
    @Override
    public HttpEntity<?> deleteById(Long id) {
        return service.deleteById(id);
    }


    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){
        return service.getAll();
    }

    @GetMapping("/getStatus")
    public HttpEntity<?> getAllStatus(){
        return service.getAllStatus();
    }

    @GetMapping("getArchivedById/{id}")
    public HttpEntity<?> getArchived(@PathVariable Long id){
        return service.getArchived(id);
    }

    @GetMapping("/getAllArchived")
    public HttpEntity<?> getAllArchved(){
        return service.getAllArchived();
    }
}
