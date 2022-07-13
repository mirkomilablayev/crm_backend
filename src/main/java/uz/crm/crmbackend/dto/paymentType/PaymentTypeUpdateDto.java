package uz.crm.crmbackend.dto.paymentType;


import lombok.*;
import uz.crm.crmbackend.dto.Dto;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTypeUpdateDto implements Dto {
    private Long id;
    private String name;
}
