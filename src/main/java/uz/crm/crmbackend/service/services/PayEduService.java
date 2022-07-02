package uz.crm.crmbackend.service.services;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.eduCenterPay.PayEduCreateDto;
import uz.crm.crmbackend.dto.eduCenterPay.PayEduUpdateDto;
import uz.crm.crmbackend.repository.repositories.PayEduRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;

@Service
public class PayEduService extends AbstractService<PayEduRepo> implements BaseService, CrudService<PayEduCreateDto, PayEduUpdateDto> {
    public PayEduService(PayEduRepo repository) {
        super(repository);
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
