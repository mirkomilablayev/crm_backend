package uz.crm.crmbackend.dto.paymentType;


import lombok.*;
import uz.crm.crmbackend.dto.Dto;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaymentTypeCreateDto implements Dto {
    private String name;
}
