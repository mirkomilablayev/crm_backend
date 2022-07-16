package uz.crm.crmbackend.dto.subject;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SubjectShowDto {
    private Long id;
    private String name;
    private String comment;
}
