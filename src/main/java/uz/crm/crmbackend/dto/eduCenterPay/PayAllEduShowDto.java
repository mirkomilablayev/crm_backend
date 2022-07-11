package uz.crm.crmbackend.dto.eduCenterPay;


import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PayAllEduShowDto {
    private Long id;
    private Long eduCenterId;
    private String eduCenterName;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private Double amount;

}
