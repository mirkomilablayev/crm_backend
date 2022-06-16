package uz.crm.crmbackend.entity;

import lombok.*;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoomBusyTime extends BaseEntityId implements BaseEntity {
    @ManyToOne
    private Room room;
    @ManyToOne
    private Group group;
    private LocalDate busyDate;
    private LocalTime busyFrom;
    private LocalTime busyTo;
}
