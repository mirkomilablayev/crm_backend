package uz.crm.crmbackend.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.crm.crmbackend.dto.Dto;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;

public interface CrudController<
        DTO extends BaseEntity,
        UpDto extends Dto> {

     HttpEntity<?> create(@RequestBody DTO cd);

     HttpEntity<?> update(@RequestBody UpDto cd);

     HttpEntity<?> get(@PathVariable Long id);

     HttpEntity<?> deleteById(@PathVariable Long id);
}
