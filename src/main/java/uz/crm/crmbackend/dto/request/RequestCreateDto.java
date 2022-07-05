package uz.crm.crmbackend.dto.request;

import lombok.*;
import uz.crm.crmbackend.dto.Dto;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateDto implements Dto {
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String eduCenterName;

}
