package uz.crm.crmbackend.entity;

import lombok.*;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "edu_center")
public class EduCenter extends BaseEntityId implements BaseEntity {
    private String center_name;
    private String address;
    private String center_phone;
    private String ceo_name;
    private String ceo_tell;
    private String center_stir;

}
