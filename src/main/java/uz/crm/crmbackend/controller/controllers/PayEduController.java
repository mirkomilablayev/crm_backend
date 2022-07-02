package uz.crm.crmbackend.controller.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @Override
    public HttpEntity<?> create(PayEduCreateDto cd) {
        return null;
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
}
