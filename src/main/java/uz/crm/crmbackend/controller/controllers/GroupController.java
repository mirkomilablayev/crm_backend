package uz.crm.crmbackend.controller.controllers;


import lombok.*;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.controller.CrudController;
import uz.crm.crmbackend.dto.group.GroupCreateDto;
import uz.crm.crmbackend.dto.group.GroupUpdateDto;
import uz.crm.crmbackend.service.services.GroupService;

@RestController
@RequestMapping("/api/group")
public class GroupController extends AbstractController<GroupService> implements CrudController<GroupCreateDto, GroupUpdateDto> {

    public GroupController(GroupService service) {
        super(service);
    }

    @Override
    public HttpEntity<?> create(GroupCreateDto cd) {
        return null;
    }

    @Override
    public HttpEntity<?> update(GroupUpdateDto cd) {
        return null;
    }

    @Override
    public HttpEntity<?> get(Long id) {
        return null;
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        return null;
    }
}
