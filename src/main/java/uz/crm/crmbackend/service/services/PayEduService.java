package uz.crm.crmbackend.service.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import uz.crm.crmbackend.dto.eduCenterPay.PayEduCreateDto;
import uz.crm.crmbackend.dto.eduCenterPay.PayEduShow;
import uz.crm.crmbackend.dto.eduCenterPay.PayEduUpdateDto;
import uz.crm.crmbackend.dto.eduCenterPay.Pays;
import uz.crm.crmbackend.entity.PayEdu;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.repository.repositories.EduCenterRepo;
import uz.crm.crmbackend.repository.repositories.PayEduRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;

import java.util.ArrayList;
import java.util.List;

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
        List<PayEdu> payEduList = repository.findAllByEduCenter_Id(eduCenterId);
        return ResponseEntity.status(HttpStatus.OK).body(makePayEduShow(payEduList,eduCenterId));
    }

    private PayEduShow makePayEduShow(List<PayEdu> payEduList, Long eduCenterId) {
        PayEduShow payEduShow = new PayEduShow();
        payEduShow.setEduCenterId(eduCenterId);
        payEduShow.setEduCenterName(
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
        payEduShow.setPaysList(pays);
        return payEduShow;
    }
}
