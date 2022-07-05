package uz.crm.crmbackend.entity;


import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RequestToGetDemo extends BaseEntityId implements BaseEntity {
    private String fullName;
    private String phoneNumber;
    private String eduCenterName;
    private LocalDateTime sentAt;
    private Boolean isActive = true;
}
