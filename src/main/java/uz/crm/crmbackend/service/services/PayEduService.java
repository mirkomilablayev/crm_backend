package uz.crm.crmbackend.service.services;

import lombok.*;
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
import java.util.*;
import java.util.stream.Collectors;


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
        Optional<PayEdu> payEduOptional = repository.findLastPayment(cd.getEduCenterId());

        if (payEduOptional.isPresent()) {
            PayEdu payEdu = payEduOptional.get();
            if (payEdu.getEndTime().isBefore(cd.getStartTime()) || payEdu.getEndTime().isEqual(cd.getEndTime())) {
                EduCenter eduCenter = eduCenterRepo
                        .findByIdAndIsArchived(cd.getEduCenterId(), false)
                        .orElseThrow(ResourceNotFoundException::new);
                eduCenter.setCenterStatus(centerStatusRepo.findByName(Constant.status1).orElseThrow(ResourceNotFoundException::new));
                return savePayEdu(cd);
            } else
                throw new ConflictException("Siz Kiritgan Vaqtlarda Xatoliklar Bor");
        } else {
            return savePayEdu(cd);
        }
    }

    private ResponseEntity<PayEdu> savePayEdu(PayEduCreateDto cd) {
        if ((cd.getStartTime().isEqual(LocalDateTime.now()) || cd.getStartTime().isAfter(LocalDateTime.now())) && cd.getStartTime().isBefore(cd.getEndTime())) {
            return ResponseEntity.status(HttpStatus.OK).body(repository.save(new PayEdu(eduCenterRepo.findByIdAndIsArchived(cd.getEduCenterId(), false).orElseThrow(ResourceNotFoundException::new), cd.getStartTime(), cd.getEndTime(), cd.getPayAmount(), cd.getComment())));
        } else {
            throw new ConflictException("Siz Kiritgan Vaqtlarda Xatoliklar Bor");
        }
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
        res.setEduCenterName(eduCenterRepo.findByIdAndIsArchived(eduCenterId, false).orElseThrow(ResourceNotFoundException::new).getEdu_centerName());
        all.forEach(payEdu -> {
            Pays pays = new Pays();
            pays.setStartTime(payEdu.getStartTime());
            pays.setEndTime(payEdu.getEndTime());
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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new PayAmount(
                        repository.getAllPayAmountSum()
                ));
    }

    @Scheduled(fixedRate = 60 * 60 * 12 * 1000)
    public void makeUnActiveEduCenter() {
        System.out.println("Shu funksiya ishladi");
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
        List<PayAllEduShowDto> res = new ArrayList<>();
        for (Long id : eduCenterRepo.getIds()) {
            Optional<PayEdu> lastPaymentOptional = repository.findLastPayment(id);
            if (lastPaymentOptional.isPresent()) {
                PayEdu payEdu = lastPaymentOptional.get();
                PayAllEduShowDto item = new PayAllEduShowDto();

                item.setComment(payEdu.getComment());
                item.setEduCenterId(id);
                item.setAmount(payEdu.getPayAmount());
                item.setFromDate(payEdu.getStartTime());
                item.setToDate(payEdu.getEndTime());
                item.setEduCenterName(eduCenterRepo.findById(id).orElseThrow(ResourceNotFoundException::new).getEdu_centerName());

                res.add(item);
            }
        }
        return ResponseEntity.ok(res);
    }


}
