package uz.crm.crmbackend.dto.eduCenter;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EduCenterShowDto {
    private Long id;
    private String eduCenterName;
    private LocalDateTime joiningAt;
    private String status;
    private String phoneNumber;
    private String ceo;
}