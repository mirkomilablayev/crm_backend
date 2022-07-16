package uz.crm.crmbackend.controller.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.controller.CrudController;
import uz.crm.crmbackend.dto.subject.SubjectCreateDto;
import uz.crm.crmbackend.dto.subject.SubjectUpdateDto;
import uz.crm.crmbackend.service.services.SubjectService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/subject")
public class SubjectController extends AbstractController<SubjectService> implements CrudController<SubjectCreateDto, SubjectUpdateDto> {


    public SubjectController(SubjectService service) {
        super(service);
    }

    @PostMapping("/createSubject")
    @Override
    public HttpEntity<?> create(SubjectCreateDto cd) {
        return service.create(cd);
    }

    @PutMapping("/updateSubject")
    @Override
    public HttpEntity<?> update(SubjectUpdateDto cd) {
        return service.update(cd);
    }

    @GetMapping("/getSubject/{id}")
    @Override
    public HttpEntity<?> get(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("/deleteSubject/{id}")
    @Override
    public HttpEntity<?> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAllActive(){
        return service.getAllActive();
    }


    @PostMapping("/changeStatus")
    public HttpEntity<?> changeStatus(@RequestParam Long id){
        return service.changeStatus(id);
    }
}
