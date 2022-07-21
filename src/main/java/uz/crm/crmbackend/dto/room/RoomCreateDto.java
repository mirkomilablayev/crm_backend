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
    private String comment;
    private String color;
}
