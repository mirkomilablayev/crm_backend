package uz.crm.crmbackend.entity;


import lombok.*;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class PaymentType extends BaseEntityId implements BaseEntity {
    private String name;
    private Boolean isActive = true;
    @ManyToOne
    private EduCenter eduCenter;
}
