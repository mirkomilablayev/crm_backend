package uz.crm.crmbackend.service.services;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.crm.crmbackend.dto.room.RoomCreateDto;
import uz.crm.crmbackend.dto.room.RoomShowDto;
import uz.crm.crmbackend.dto.room.RoomUpdateDto;
import uz.crm.crmbackend.entity.Room;
import uz.crm.crmbackend.exceptions.ConflictException;
import uz.crm.crmbackend.exceptions.ResourceAlreadyExistsException;
import uz.crm.crmbackend.exceptions.ResourceNotFoundException;
import uz.crm.crmbackend.repository.repositories.RoomRepo;
import uz.crm.crmbackend.service.AbstractService;
import uz.crm.crmbackend.service.CrudService;
import uz.crm.crmbackend.tools.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        if (repository.existsByNameAndEduCenter_Id(cd.getName(), eduCenterId)) {
            Room room = new Room();
            room.setCreatedAt(LocalDateTime.now());
            if (cd.getRoomCapacity() > 0) {
                room.setRoomCapacity(cd.getRoomCapacity());
            } else {
                throw new ConflictException(room.getName() + "'s room capacity is invalid");
            }

            room.setComment(cd.getComment());
            room.setEduCenter(util.getCurrentUser().getEduCenter());
            room.setIsActive(true);
            room.setName(cd.getName());
            Room save = repository.save(room);
            return ResponseEntity.status(HttpStatus.OK).body(save);
        } else {
            throw new ResourceAlreadyExistsException(cd.getName() + " is already exists");
        }
    }

    @Override
    public HttpEntity<?> update(RoomUpdateDto cd) {
        Room room = repository.findByIdAndIsActive(cd.getId(), true).orElseThrow(ResourceNotFoundException::new);

        if (cd.getName() != null && !util.checkBlank(cd.getName()) && !room.getName().equals(cd.getName())) {
            room.setName(cd.getName());
        }

        if (cd.getComment() != null && !util.checkBlank(cd.getComment()) && !room.getComment().equals(cd.getComment())) {
            room.setComment(cd.getComment());
        }

        if (cd.getRoomCapacity() != null && room.getRoomCapacity().equals(cd.getRoomCapacity()) && room.getAvailableStudent() >= cd.getRoomCapacity()) {
            room.setRoomCapacity(cd.getRoomCapacity());
        }

        Room save = repository.save(room);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

    /**
     * private Long id
     * private String name;
     * private LocalDateTime CreatedAt;
     * private String eduCenterName;
     * private Long eduCenterId;
     * private String comment;
     * private Long roomCapacity;
     * private Long availableStudent;
     * private Boolean isActive = true;
     */

    @Override
    public HttpEntity<?> get(Long id) {
        RoomShowDto res = makeRoomShowDto(repository.findByIdAndIsActive(id, true).orElseThrow(ResourceNotFoundException::new));
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    private RoomShowDto makeRoomShowDto(Room room) {
        RoomShowDto res = new RoomShowDto();
        res.setComment(room.getComment());
        res.setName(room.getName());
        res.setAvailableStudent(room.getAvailableStudent());
        res.setEduCenterId(room.getEduCenter().getId());
        res.setEduCenterName(room.getEduCenter().getEdu_centerName());
        res.setId(room.getId());
        res.setIsActive(room.getIsActive());
        res.setRoomCapacity(room.getRoomCapacity());
        res.setCreatedAt(room.getCreatedAt());
        return res;
    }

    @Override
    public HttpEntity<?> deleteById(Long id) {
        Room room = repository.findByIdAndIsActive(id, true).orElseThrow(ResourceNotFoundException::new);
        room.setIsActive(false);
        Room save = repository.save(room);
        return ResponseEntity.status(HttpStatus.OK).body(save.getName()+" room is successfully deleted");
    }

    public HttpEntity<?> getAllRooms() {
        List<RoomShowDto> res = new ArrayList<>();
        for (Room room : repository.findAllByEduCenter_IdAndIsActive(util.getEduCenterId(), true)) {
            res.add(makeRoomShowDto(room));
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
