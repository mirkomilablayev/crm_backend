package uz.crm.crmbackend.entity;

import lombok.*;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Column;
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
    @Column(nullable = false)
    private String edu_centerName;
    @Column(nullable = false)
    private String centerPhone;
    @Column(nullable = false)
    private String ceo_full_name;
    @Column(nullable = false)
    private String ceoPhone;
    @Column(nullable = false)
    private String centerStir;
    @Column(nullable = false)
    private Boolean isArchived = false;
    @ManyToOne
    private CenterStatus centerStatus;
    private LocalDateTime addedAt = LocalDateTime.now();

}
