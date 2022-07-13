package uz.crm.crmbackend.service.services;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.room.RoomCreateDto;
import uz.crm.crmbackend.dto.room.RoomUpdateDto;
import uz.crm.crmbackend.repository.repositories.RoomRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.CrudService;
import uz.crm.crmbackend.tools.Util;

@Service
public class RoomService extends AbstractService<RoomRepo> implements CrudService<RoomCreateDto, RoomUpdateDto> {
    private final Util util;

    public RoomService(RoomRepo repository, Util util) {
        super(repository);
        this.util = util;
    }

    @Override
    public HttpEntity<?> create(RoomCreateDto cd) {
        Long eduCenterId = util.getEduCenterId();
        if (repository.existsByNameAndEduCenter_Id(cd.getName(), eduCenterId)){

        }
        return null;
    }

    @Override
    public HttpEntity<?> update(RoomUpdateDto cd) {
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
