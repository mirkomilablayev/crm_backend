package uz.crm.crmbackend.controller.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.controller.CrudController;
import uz.crm.crmbackend.dto.paymentType.PaymentTypeCreateDto;
import uz.crm.crmbackend.dto.paymentType.PaymentTypeUpdateDto;
import uz.crm.crmbackend.service.services.PaymentTypeService;

import javax.ws.rs.Path;

@RestController
@RequestMapping("/api/paymentType")
public class PaymentTypeController extends AbstractController<PaymentTypeService> implements CrudController<PaymentTypeCreateDto, PaymentTypeUpdateDto> {

    public PaymentTypeController(PaymentTypeService service) {
        super(service);
    }

    @PostMapping("/createPaymentType")
    @Override
    public HttpEntity<?> create(PaymentTypeCreateDto cd) {
        return service.create(cd);
    }


    @PutMapping("/updatePaymentType")
    @Override
    public HttpEntity<?> update(PaymentTypeUpdateDto cd) {
        return service.update(cd);
    }

    @GetMapping("/getById/{id}")
    @Override
    public HttpEntity<?> get(@PathVariable Long id) {
        return service.get(id);
    }

    @DeleteMapping("/deleteById/{id}")
    @Override
    public HttpEntity<?> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/getAll")
    public HttpEntity<?> getAll(){
        return service.getAll();
    }
}
