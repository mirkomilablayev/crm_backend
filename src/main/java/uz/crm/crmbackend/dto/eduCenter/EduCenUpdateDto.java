package uz.crm.crmbackend.dto.eduCenter;

import lombok.Data;
import uz.crm.crmbackend.dto.Dto;

import java.time.LocalDateTime;

@Data
public class EduCenUpdateDto implements Dto {
    private Long eduCenterId;
    private String edu_centerName;
    private String centerPhone;
    private String ceo_full_name;
    private String ceoPhone;
    private String centerStir;
    private String centerStatusName;
    private Long logoId;

}
