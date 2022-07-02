package uz.crm.crmbackend.dto.eduCenterPay;

import lombok.*;
import uz.crm.crmbackend.dto.Dto;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PayEduCreateDto implements Dto {
    private Long eduCenterId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double payAmount;
    private String comment;
}
