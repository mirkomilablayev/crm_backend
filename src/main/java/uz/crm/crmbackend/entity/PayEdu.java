package uz.crm.crmbackend.entity;

import lombok.*;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PayEdu extends BaseEntityId implements BaseEntity {
    @ManyToOne
    private EduCenter eduCenter;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double payAmount;
    private String comment;

}
