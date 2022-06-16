package uz.crm.crmbackend.entity;

import lombok.*;
import uz.crm.crmbackend.entity.EduCenter;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rooms")
public class Room extends BaseEntityId implements BaseEntity {
    private String roomName;
    @ManyToOne
    private EduCenter eduCenter;
}
