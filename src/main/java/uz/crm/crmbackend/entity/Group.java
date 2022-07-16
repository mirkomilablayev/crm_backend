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
@Entity(name = "groups")
public class Group extends BaseEntityId implements BaseEntity {
    private String groupName;
    private LocalDateTime createdAt;
    @ManyToOne
    private User teacher;
    @ManyToOne
    private Room room;
    private String comment;
    private Boolean isActive = true;
}
