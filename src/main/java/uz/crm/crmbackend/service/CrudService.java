package uz.crm.crmbackend.service;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import uz.crm.crmbackend.dto.Dto;
import uz.crm.crmbackend.dto.UpDto;

public interface CrudService<
        DTO extends Dto,
        UpDto_ extends UpDto> {

    HttpEntity<?> create(DTO cd);

    HttpEntity<?> update(UpDto_ cd);

    HttpEntity<?> get(@PathVariable Long id);

    HttpEntity<?> deleteById(@PathVariable Long id);
}
