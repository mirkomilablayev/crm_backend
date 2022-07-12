package uz.crm.crmbackend.service.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.eduCenterPay.*;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.entity.PayEdu;
import uz.crm.crmbackend.exceptions.ConflictException;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.repository.repositories.CenterStatusRepo;
import uz.crm.crmbackend.repository.repositories.EduCenterRepo;
import uz.crm.crmbackend.repository.repositories.PayEduRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;
import uz.crm.crmbackend.tools.Constant;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// ctrl + c
@Service
public class PayEduService extends AbstractService<PayEduRepo> implements BaseService, CrudService<PayEduCreateDto, PayEduUpdateDto> {
    private final EduCenterRepo eduCenterRepo;
    private final CenterStatusRepo centerStatusRepo;

    public PayEduService(PayEduRepo repository, EduCenterRepo eduCenterRepo, CenterStatusRepo centerStatusRepo) {
        super(repository);
        this.eduCenterRepo = eduCenterRepo;
        this.centerStatusRepo = centerStatusRepo;
    }


    @Override
    public HttpEntity<?> create(PayEduCreateDto cd) {
        Optional<PayEdu> payEduOptional = repository.findByEduCenter_IdAndIsActiveNow(cd.getEduCenterId(), true);

        if (payEduOptional.isPresent()) {
            PayEdu payEdu = payEduOptional.get();
            payEdu.setIsActiveNow(false);
            repository.save(payEdu);
            EduCenter eduCenter = eduCenterRepo.findByIdAndIsArchived(cd.getEduCenterId(), false).orElseThrow(ResourceNotFoundException::new);
            eduCenter.setCenterStatus(centerStatusRepo.findByName(Constant.status1).orElseThrow(ResourceNotFoundException::new));
            return savePayEdu(cd);
        } else {
            return savePayEdu(cd);
        }
    }

    private ResponseEntity<PayEdu> savePayEdu(PayEduCreateDto cd) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(new PayEdu(eduCenterRepo.findByIdAndIsArchived(cd.getEduCenterId(), false).orElseThrow(ResourceNotFoundException::new), cd.getStartTime(), cd.getEndTime(), cd.getPayAmount(), cd.getComment())));
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
        Optional<PayEdu> optionalPayEdu = repository.findByEduCenter_IdAndIsActiveNow(eduCenterId, true);

        if (!eduCenterRepo.existsByIdAndIsArchived(eduCenterId, false))
            throw new ConflictException("This edu center doesn't exists");

        return ResponseEntity.status(HttpStatus.OK).body(optionalPayEdu.map(payEdu -> new LastPaymentDateDto(payEdu.getEndTime(), payEdu.getEndTime().plusMonths(1))).orElseGet(() -> new LastPaymentDateDto(LocalDateTime.now(), LocalDateTime.now().plusMonths(1))));
    }

    public HttpEntity<?> getPaymentsCount() {
        double  aDouble = repository.getAllPayAmountSum();
        PayAmount payAmount = new PayAmount(aDouble);
        return ResponseEntity.status(HttpStatus.OK).body(payAmount);
    }

    @Scheduled(fixedRate = 60 * 60 * 12 * 1000)
    public void makeUnActiveEduCenter(){
        repository.findByIsActiveNow(true).forEach(payEdu -> {
            if (payEdu.getEndTime().isAfter(LocalDateTime.now())) {
                EduCenter eduCenter = payEdu.getEduCenter();
                eduCenter.setCenterStatus(centerStatusRepo.findByName(Constant.status3).orElseThrow(ResourceNotFoundException::new));
                eduCenterRepo.save(eduCenter);
                payEdu.setIsActiveNow(false);
                repository.save(payEdu);
            }
        });
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
            item.setComment(a.getComment());
            res.add(item);
        });
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
