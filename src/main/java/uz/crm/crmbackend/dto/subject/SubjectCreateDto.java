package uz.crm.crmbackend.dto.subject;


import lombok.*;
import uz.crm.crmbackend.dto.Dto;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCreateDto implements Dto {
    private String subjectName;
    private String comment;
}
