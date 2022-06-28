package uz.crm.crmbackend.service;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import uz.crm.crmbackend.dto.Dto;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;

public interface CrudService<
        DTO extends Dto,
        UpDto extends Dto> {

    HttpEntity<?> create(DTO cd);

    HttpEntity<?> update(UpDto cd);

    HttpEntity<?> get(Long id);

    HttpEntity<?> deleteById(Long id);
}
