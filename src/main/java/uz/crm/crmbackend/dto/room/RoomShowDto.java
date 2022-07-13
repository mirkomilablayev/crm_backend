package uz.crm.crmbackend.dto.room;

import lombok.*;
import uz.crm.crmbackend.entity.EduCenter;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomShowDto {
    private Long id;
    private String name;
    private LocalDateTime CreatedAt;
    private String eduCenterName;
    private Long eduCenterId;
    private String comment;
    private Long roomCapacity;
    private Long availableStudent;
    private Boolean isActive = true;
}
