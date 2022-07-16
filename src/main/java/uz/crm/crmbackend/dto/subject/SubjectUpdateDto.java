package uz.crm.crmbackend.dto.subject;

import lombok.*;
import uz.crm.crmbackend.dto.Dto;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubjectUpdateDto implements Dto {
    private Long id;
    private String name;
    private String comment;
}
