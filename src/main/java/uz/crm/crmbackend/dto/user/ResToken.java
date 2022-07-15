package uz.crm.crmbackend.dto.user;

import lombok.*;
import uz.crm.crmbackend.entity.UserRole;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResToken {
    private String token;
    private String userName;
    private Long userId;
    private String eduCenterName;
    private Long eduCenterId;
    private Long imgId;
    private Set<String> userRoleSet;
}
