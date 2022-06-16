package uz.crm.crmbackend.entity;

import lombok.*;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PaymentForDate extends BaseEntityId implements BaseEntity {
    private double amount;
    @ManyToOne
    private RoomBusyTime busyTime;
    @ManyToOne
    private User student;
    private Boolean isComplete;
}
