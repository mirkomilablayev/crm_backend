package uz.crm.crmbackend.entity;


import lombok.*;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Room extends BaseEntityId implements BaseEntity {
    private String name;
    private LocalDateTime CreatedAt;
    @ManyToOne
    private EduCenter eduCenter;
    private String comment;
    private String color;
    private Boolean isActive = true;
}
