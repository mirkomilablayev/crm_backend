package uz.crm.crmbackend.controller.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.dto.room.RoomCreateDto;
import uz.crm.crmbackend.dto.room.RoomUpdateDto;
import uz.crm.crmbackend.service.CrudService;
import uz.crm.crmbackend.service.services.RoomService;

@RestController
@RequestMapping("/api/room")
public class RoomController extends AbstractController<RoomService> implements CrudService<RoomCreateDto, RoomUpdateDto> {
    public RoomController(RoomService service) {
        super(service);
    }

    @Override
    public HttpEntity<?> create(RoomCreateDto cd) {
        return service.create(cd);
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
