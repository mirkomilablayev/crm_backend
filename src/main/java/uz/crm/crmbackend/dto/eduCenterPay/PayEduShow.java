package uz.crm.crmbackend.dto.eduCenterPay;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PayEduShow {
    private Long eduCenterId;
    private String eduCenterName;
    List<Pays> paysList;
}
