package uz.crm.crmbackend.dto.eduCenterPay;

import lombok.*;
import uz.crm.crmbackend.dto.Dto;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PayEduShowDto implements Dto {
    private Long eduCenterId;
    private String eduCenterName;
    List<Pays> paysList;
}
