package uz.crm.crmbackend.dto.eduCenterPay;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LastPaymentDateDto {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
