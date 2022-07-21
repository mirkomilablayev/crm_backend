package uz.crm.crmbackend.entity;

import lombok.*;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@ToString
@Entity
public class Subject extends BaseEntityId implements BaseEntity {
    private String name;
    private String comment;
    private Boolean isActive = true;
    private Boolean isDeleted = false;
    @ManyToOne
    private EduCenter eduCenter;
}
