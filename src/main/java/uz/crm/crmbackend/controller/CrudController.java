package uz.crm.crmbackend.controller;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.crm.crmbackend.dto.Dto;
import uz.crm.crmbackend.dto.UpDto;

public interface CrudController<
        DTO extends Dto,
        UpDto_ extends UpDto> {

     HttpEntity<?> create(@RequestBody DTO cd);

     HttpEntity<?> update(@RequestBody UpDto_ cd);

     HttpEntity<?> get(@PathVariable Long id);

     HttpEntity<?> deleteById(@PathVariable Long id);
}
