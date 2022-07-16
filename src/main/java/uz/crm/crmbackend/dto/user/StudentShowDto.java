package uz.crm.crmbackend.dto.user;

import lombok.*;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.entity.File;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentShowDto {
    private String fullName;
    private String phoneNumber;
    private Long eduCenterId;
    private Long logoFileId;
    private String relativesPhoneNumber;
    private LocalDate createdAt;
    private String username;
    private String pass;
}
