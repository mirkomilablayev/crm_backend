package uz.crm.crmbackend.entity;

import lombok.*;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class CenterStatus extends BaseEntityId implements BaseEntity {
    private String name;


    public CenterStatus(String name){
        this.name = name;
    }
}
