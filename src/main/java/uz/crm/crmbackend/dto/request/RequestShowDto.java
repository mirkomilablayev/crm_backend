package uz.crm.crmbackend.dto.request;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestShowDto {
    private String fullName;
    private String phoneNumber;
    private String eduCenterName;
    private LocalDateTime sentAt;
}
