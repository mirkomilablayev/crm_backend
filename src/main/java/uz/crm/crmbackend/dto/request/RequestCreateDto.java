package uz.crm.crmbackend.dto.request;

import lombok.*;
import uz.crm.crmbackend.dto.Dto;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateDto implements Dto {
    private String fullName;
    private String phoneNumber;
    private LocalDateTime sentAt;
}
