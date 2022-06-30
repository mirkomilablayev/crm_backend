package uz.crm.crmbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.Entity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "files")
public class File extends BaseEntityId implements BaseEntity {
    private String originalName;
    private String generatedName;
    private String file_path;
    private Long size;
    private String content_type;
    private Boolean isActive = true;
}
