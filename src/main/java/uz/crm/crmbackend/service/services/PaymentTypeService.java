package uz.crm.crmbackend.service.services;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.crm.crmbackend.dto.paymentType.PaymentTypeCreateDto;
import uz.crm.crmbackend.dto.paymentType.PaymentTypeUpdateDto;
import uz.crm.crmbackend.entity.PaymentType;
import uz.crm.crmbackend.exceptions.ResourceAlreadyExistsException;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.repository.repositories.PaymentTypeRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;
import uz.crm.crmbackend.tools.Util;

@Service
public class PaymentTypeService extends AbstractService<PaymentTypeRepo> implements BaseService, CrudService<PaymentTypeCreateDto, PaymentTypeUpdateDto> {
   private final Util util;
    public PaymentTypeService(Util util, PaymentTypeRepo repository) {
        super(repository);
        this.util = util;
    }

    @Override
    public HttpEntity<?> create(PaymentTypeCreateDto cd) {
        if (repository.existsByNameAndIsActiveAndEduCenter_Id(cd.getName(), true,util.getEduCenterId())){
            PaymentType save = repository.save(new PaymentType(cd.getName(), true,util.getCurrentUser().getEduCenter()));
            return ResponseEntity.status(HttpStatus.OK).body(save);
        }else{
            throw new ResourceAlreadyExistsException(cd.getName() + " is Already exists");
        }
    }

    @Override
    public HttpEntity<?> update(PaymentTypeUpdateDto cd) {
        PaymentType paymentType = repository.findByIdAndIsActive(cd.getId(), true).orElseThrow(ResourceNotFoundException::new);
        paymentType.setName(cd.getName());
        PaymentType save = repository.save(paymentType);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

    @Override
    public HttpEntity<?> get(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findByIdAndIsActive(id, true).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        PaymentType paymentType = repository.findByIdAndIsActive(id, true).orElseThrow(ResourceNotFoundException::new);
        paymentType.setIsActive(false);
        repository.save(paymentType);
        return ResponseEntity.status(HttpStatus.OK).body(paymentType.getName() + " is successfully Deleted");
    }

    public HttpEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAllByEduCenter_IdAndIsActive(util.getEduCenterId(), true));
    }
}
