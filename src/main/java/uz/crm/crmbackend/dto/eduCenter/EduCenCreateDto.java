package uz.crm.crmbackend.dto.eduCenter;

import lombok.*;
import uz.crm.crmbackend.dto.Dto;
import uz.crm.crmbackend.entity.CenterStatus;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class EduCenCreateDto implements Dto {
    private String edu_centerName;
    private String centerPhone;
    private String ceo_full_name;
    private String ceoPhone;
    private String centerStir;
    private String centerStatusName;
    private LocalDateTime joiningStart;
    private LocalDateTime joiningEnd;
    private String adminFullName;
    private String adminUsername;
    private String adminPassword;
    private Long logoId;

}
