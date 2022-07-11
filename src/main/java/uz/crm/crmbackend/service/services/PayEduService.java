package uz.crm.crmbackend.service.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.eduCenterPay.*;
import uz.crm.crmbackend.entity.PayEdu;
import uz.crm.crmbackend.exceptions.ConflictException;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.repository.repositories.EduCenterRepo;
import uz.crm.crmbackend.repository.repositories.PayEduRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PayEduService extends AbstractService<PayEduRepo> implements BaseService, CrudService<PayEduCreateDto, PayEduUpdateDto> {
    private final EduCenterRepo eduCenterRepo;

    public PayEduService(PayEduRepo repository, EduCenterRepo eduCenterRepo) {
        super(repository);
        this.eduCenterRepo = eduCenterRepo;
    }


    @Override
    public HttpEntity<?> create(PayEduCreateDto cd) {
        Optional<PayEdu> payEduOptional = repository
                .findByEduCenter_IdAndIsActiveNow(cd.getEduCenterId(), true);

        if (payEduOptional.isPresent()) {
            PayEdu payEdu = payEduOptional.get();
            payEdu.setIsActiveNow(false);
            repository.save(payEdu);
            return savePayEdu(cd);
        } else {
            return savePayEdu(cd);
        }
    }

    private ResponseEntity<PayEdu> savePayEdu(PayEduCreateDto cd) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(repository.save(new PayEdu(
                        eduCenterRepo.findByIdAndIsArchived(cd.getEduCenterId(), false)
                                .orElseThrow(ResourceNotFoundException::new),
                        cd.getStartTime(),
                        cd.getEndTime(),
                        cd.getPayAmount(),
                        cd.getComment()
                )));
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


    public HttpEntity<?> getEducationPayments(Long eduCenterId) {
        List<PayEdu> all = repository.findAllByEduCenter_Id(eduCenterId);
        PayEduShowDto res = new PayEduShowDto();
        List<Pays> paysList = new ArrayList<>();
        res.setEduCenterId(eduCenterId);
        res.setEduCenterName(eduCenterRepo.findByIdAndIsArchived(eduCenterId, false).get().getEdu_centerName());
        all.forEach(payEdu -> {
            Pays pays = new Pays();
            pays.setStartTime(payEdu.getStartTime());
            pays.setStartEnd(payEdu.getEndTime());
            pays.setPayAmount(payEdu.getPayAmount());
            paysList.add(pays);
        });
        res.setPaysList(paysList);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    public HttpEntity<?> getLastPaymentDate(Long eduCenterId) {
        Optional<PayEdu> optionalPayEdu = repository
                .findByEduCenter_IdAndIsActiveNow(eduCenterId, true);

        if (!eduCenterRepo.existsByIdAndIsArchived(eduCenterId, false))
            throw new ConflictException("This edu center doesn't exists");

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(optionalPayEdu.map(payEdu -> new LastPaymentDateDto(payEdu.getEndTime(), payEdu.getEndTime().plusMonths(1))).orElseGet(() -> new LastPaymentDateDto(LocalDateTime.now(), LocalDateTime.now().plusMonths(1))));
    }

    public HttpEntity<?> getPaymentsCount() {
        return ResponseEntity.status(HttpStatus.OK).body(new PayAmount(repository.sumAllPayments()));
    }

    public HttpEntity<?> getAllPayments() {
        List<PayEdu> list = repository.findByIsActiveNow(true);
        List<PayAllEduShowDto> res = new ArrayList<>();
        list.forEach(a -> {
            PayAllEduShowDto item = new PayAllEduShowDto();
            item.setAmount(a.getPayAmount());
            item.setFromDate(a.getStartTime());
            item.setToDate(a.getEndTime());
            item.setEduCenterId(a.getEduCenter().getId());
            item.setEduCenterName(a.getEduCenter().getEdu_centerName());
            item.setId(a.getId());
            res.add(item);
        });
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
