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
@Entity(name = "groups")
public class Group extends BaseEntityId implements BaseEntity {
    private String lessonName;
    @ManyToOne
    private User groupTeacher;
    @ManyToOne
    private User student;
    @ManyToOne
    private Room room;
}
