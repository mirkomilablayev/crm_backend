package uz.crm.crmbackend.dto.eduCenterPay;

import lombok.*;
import uz.crm.crmbackend.dto.Dto;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PayEduCreateDto implements Dto {
    @Column(nullable = false)
    private Long eduCenterId;
    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;
    @Column(nullable = false)
    private Double payAmount;
    private String comment;
}
