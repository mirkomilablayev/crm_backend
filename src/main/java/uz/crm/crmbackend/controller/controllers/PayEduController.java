package uz.crm.crmbackend.controller.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.controller.CrudController;
import uz.crm.crmbackend.dto.eduCenterPay.PayEduCreateDto;
import uz.crm.crmbackend.dto.eduCenterPay.PayEduUpdateDto;
import uz.crm.crmbackend.service.services.PayEduService;

@RestController
@RequestMapping("/api/payEdu")
public class PayEduController extends AbstractController<PayEduService> implements CrudController<PayEduCreateDto, PayEduUpdateDto> {
    public PayEduController(PayEduService service) {
        super(service);
    }

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

    @GetMapping("/getLastPaymentInfo/{eduCenterId}")
    public HttpEntity<?> getPaymentInformation(@PathVariable Long eduCenterId){
        return service.getLastPaymentInformation(eduCenterId);
    }
}
