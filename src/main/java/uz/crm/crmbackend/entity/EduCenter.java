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
@Entity(name = "edu_center")
public class EduCenter extends BaseEntityId implements BaseEntity {
    private String edu_centerName;
    private String centerPhone;
    private String ceo_full_name;
    private String ceoPhone;
    private String centerStir;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Boolean isArchived = false;


    @ManyToOne
    private CenterStatus centerStatus;
    @ManyToOne
    private File logoFile;
    private LocalDateTime addedAt = LocalDateTime.now();
    private LocalDateTime lastUpdatedAt;

}
