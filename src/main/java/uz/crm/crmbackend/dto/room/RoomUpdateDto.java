package uz.crm.crmbackend.dto.room;

import lombok.*;
import uz.crm.crmbackend.dto.Dto;

import java.time.LocalDateTime;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomUpdateDto implements Dto {
    private Long id;
    private String name;
    private String comment;
    private Long roomCapacity;
}
