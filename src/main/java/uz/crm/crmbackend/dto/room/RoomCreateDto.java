package uz.crm.crmbackend.dto.room;

import lombok.*;
import uz.crm.crmbackend.dto.Dto;
import uz.crm.crmbackend.entity.EduCenter;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomCreateDto implements Dto {
    private String name;
    private LocalDateTime CreatedAt;
    private EduCenter eduCenter;
    private String comment;
    private Long roomCapacity;
    private Long availableStudent;
}
