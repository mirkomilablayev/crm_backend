package uz.crm.crmbackend.controller.controllers;


import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.crm.crmbackend.controller.AbstractController;
import uz.crm.crmbackend.dto.room.RoomCreateDto;
import uz.crm.crmbackend.dto.room.RoomUpdateDto;
import uz.crm.crmbackend.service.CrudService;
import uz.crm.crmbackend.service.services.RoomService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/room")
public class RoomController extends AbstractController<RoomService> implements CrudService<RoomCreateDto, RoomUpdateDto> {
    public RoomController(RoomService service) {
        super(service);
    }

    @PostMapping("/createRoom")
    @Override
    public HttpEntity<?> create(RoomCreateDto cd) {
        return service.create(cd);
    }

    @PutMapping("/updateRoom")
    @Override
    public HttpEntity<?> update(RoomUpdateDto cd) {
        return service.update(cd);
    }

    @GetMapping("/getById/{id}")
    @Override
    public HttpEntity<?> get(@PathVariable  Long id) {
        return service.get(id);
    }

    @DeleteMapping("/deleteById/{id}")
    @Override
    public HttpEntity<?> deleteById(@PathVariable  Long id) {
        return deleteById(id);
    }


    @GetMapping("/getAllRooms")
    public HttpEntity<?> getAllRooms(){
        return service.getAllRooms();
    }
}
