package uz.crm.crmbackend.controller.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.crm.crmbackend.config.anotation.CheckRole;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.controller.CrudController;
import uz.crm.crmbackend.dto.eduCenterPay.PayEduCreateDto;
import uz.crm.crmbackend.dto.eduCenterPay.PayEduUpdateDto;
import uz.crm.crmbackend.service.services.PayEduService;
import uz.crm.crmbackend.tools.Constant;

@RestController
@RequestMapping("/api/payEdu")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PayEduController extends AbstractController<PayEduService> implements CrudController<PayEduCreateDto, PayEduUpdateDto> {
    public PayEduController(PayEduService service) {
        super(service);
    }

//    @CheckRole(Constant.SUPER_ADMIN)
    @PostMapping("/createEduPay")
    @Override
    public HttpEntity<?> create(PayEduCreateDto cd) {
        return service.create(cd);
    }

    @Override
    public HttpEntity<?> update(PayEduUpdateDto cd) {
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

    @GetMapping("/getEduPays/{eduCenterId}")
    public HttpEntity<?> getEducationPayments(@PathVariable  Long eduCenterId){
        return service.getEducationPayments(eduCenterId);
    }

    @GetMapping("/getAllActivePayments")
    public HttpEntity<?> getAllPayments(){
        return service.getAllPayments();
    }


    @PostMapping("/getLastPaymentDate/{eduCenterId}")
    public HttpEntity<?> getLastPaymentDate(@PathVariable Long eduCenterId){
        return service.getLastPaymentDate(eduCenterId);
    }


    @GetMapping("/getAllPayment")
    public HttpEntity<?> getAllPaymentCount(){
        return service.getPaymentsCount();
    }

}
