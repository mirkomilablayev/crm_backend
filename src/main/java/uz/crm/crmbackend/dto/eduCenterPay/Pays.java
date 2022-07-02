package uz.crm.crmbackend.dto.eduCenterPay;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pays {
    private Double payAmount;
    private LocalDateTime startTime;
    private LocalDateTime startEnd;
}
