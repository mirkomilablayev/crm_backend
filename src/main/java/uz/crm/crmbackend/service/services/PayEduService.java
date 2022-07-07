package uz.crm.crmbackend.service.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.eduCenterPay.*;
import uz.crm.crmbackend.entity.PayEdu;
import uz.crm.crmbackend.entity.User;
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
        if (cd.getStartTime().isBefore(cd.getEndTime()) && cd.getPayAmount() >= 0){
            PayEdu payEdu = new PayEdu(
                    eduCenterRepo.findByIdAndIsArchived(cd.getEduCenterId(),true).orElseThrow(ResourceNotFoundException::new),
                    cd.getStartTime(),
                    cd.getEndTime(),
                    cd.getPayAmount(),
                    cd.getComment()
            );
            PayEdu save = repository.save(payEdu);
            return ResponseEntity.status(HttpStatus.OK).body(save);
        }else{
            System.out.println("Shu yerda yuribmiz");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
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


    public HttpEntity<?> getEducationPayments(Long eduCenterId){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PayEdu> payEduList = repository.findAllByEduCenter_Id(eduCenterId);
        return ResponseEntity.status(HttpStatus.OK).body(makePayEduShow(payEduList,eduCenterId));
    }

    private PayEduShowDto makePayEduShow(List<PayEdu> payEduList, Long eduCenterId) {
        PayEduShowDto payEduShowDto = new PayEduShowDto();
        payEduShowDto.setEduCenterId(eduCenterId);
        payEduShowDto.setEduCenterName(
                eduCenterRepo.findByIdAndIsArchived(eduCenterId,false)
                        .orElseThrow(ResourceNotFoundException::new).getEdu_centerName()
        );
        List<Pays> pays = new ArrayList<>();
        payEduList.forEach(a -> {
            Pays b = new Pays();
            b.setPayAmount(a.getPayAmount());
            b.setStartTime(a.getStartTime());
            b.setStartEnd(a.getEndTime());
            pays.add(b);
        });
        payEduShowDto.setPaysList(pays);
        return payEduShowDto;
    }

    public HttpEntity<?> getLastPaymentInformation(Long eduCenterId) {
        Optional<PayEdu> payEduOptional = repository.findByEduCenter_IdAndIsActiveNow(eduCenterId, true);
        if (payEduOptional.isPresent()){
            PayEdu payEdu = payEduOptional.get();
            LocalDateTime endTime = payEdu.getEndTime();

        }else{

        }
        return null;
    }

    public HttpEntity<?> getLastPaymentDate(Long eduCenterId) {
        PayEdu payEdu = repository
                .findByEduCenter_IdAndIsActiveNow(eduCenterId, true)
                .orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new LastPaymentDateDto(payEdu.getEndTime(), payEdu.getEndTime().plusMonths(1)));
    }
}
