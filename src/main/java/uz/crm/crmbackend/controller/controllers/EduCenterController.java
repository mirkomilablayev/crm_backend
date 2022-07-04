package uz.crm.crmbackend.controller.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.controller.CrudController;
import uz.crm.crmbackend.dto.eduCenter.EduCenCreateDto;
import uz.crm.crmbackend.dto.eduCenter.EduCenUpdateDto;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.service.services.EduCenterService;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/eduCenter")
public class EduCenterController extends AbstractController<EduCenterService> implements CrudController<EduCenCreateDto, EduCenUpdateDto> {


    public EduCenterController(EduCenterService service) {
        super(service);
    }


    @PostMapping("/createEduCenter")
    @Override
    public HttpEntity<?> create(EduCenCreateDto cd) {
        return service.create(cd);
    }

    @PutMapping("/updateEduCenter")
    @Override
    public HttpEntity<?> update(EduCenUpdateDto cd) {
        return service.update(cd);
    }


    @GetMapping("/getOneEduCenter/{id}")
    @Override
    public HttpEntity<?> get(Long id) {
        return service.get(id);
    }

    @DeleteMapping("/delete/{id}")
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

    @GetMapping("/getArchivedById/{id}")
    public HttpEntity<?> getArchived(@PathVariable Long id){
        return service.getArchived(id);
    }

    @GetMapping("/getAllArchived")
    public HttpEntity<?> getAllArchved(){
        return service.getAllArchived();
    }

    @PostMapping("/saveFile")
    public HttpEntity<?> saveFile(MultipartHttpServletRequest request){
        return service.saveFile(request);
    }

    @GetMapping("/reStore/{eduCenterId}")
    public HttpEntity<?> restoreEduCenter(@PathVariable Long eduCenterId){
        return service.restoreEduCenter(eduCenterId);
    }


    @PutMapping("/changeStatus/{eduCenterId}")
    public HttpEntity<?> changeEduCenterStatus(@PathVariable Long eduCenterId){
        return service.changeStatus(eduCenterId);
    }


    @GetMapping("/ShowPic/{id}")
    public void showFile(@PathVariable Long id, HttpServletResponse response){
        service.showPictures(id,response);
    }

    @GetMapping("/getEducentersNameAndId")
    public HttpEntity<?> getEduCenterName(){
        return service.getEduCenterName();
    }
}
