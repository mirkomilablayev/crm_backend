package uz.crm.crmbackend.dto.eduCenter;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EduCenterPageableDto {
    private String centerName;
    private String ceoName;
    private String centerStir;
    private Long page;
    private Long size;
}
