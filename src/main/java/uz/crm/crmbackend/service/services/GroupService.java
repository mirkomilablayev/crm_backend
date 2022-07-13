package uz.crm.crmbackend.service.services;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.group.GroupCreateDto;
import uz.crm.crmbackend.dto.group.GroupUpdateDto;
import uz.crm.crmbackend.repository.repositories.GroupRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.BaseService;
import uz.crm.crmbackend.service.CrudService;

@Service
public class GroupService extends AbstractService<GroupRepo> implements BaseService, CrudService<GroupCreateDto, GroupUpdateDto> {


    public GroupService(GroupRepo repository) {
        super(repository);
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
