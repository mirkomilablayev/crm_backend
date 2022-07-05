package uz.crm.crmbackend.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private Long id;
    private String fullName;
    private LocalDateTime sentAt;
    private Boolean isSeen;
}
