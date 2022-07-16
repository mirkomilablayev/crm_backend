package uz.crm.crmbackend.dto.user;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserShowDto {
    private String fullName;
    private String phoneNumber;
    private Long eduCenterId;
    private Long logoFileId;
    private String relativesPhoneNumber;
    private LocalDate createdAt;
}
