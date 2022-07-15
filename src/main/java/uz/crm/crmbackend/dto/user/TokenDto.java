package uz.crm.crmbackend.dto.user;

import lombok.*;
import uz.crm.crmbackend.entity.UserRole;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private List<UserRolesForToken> roles;
    private String eduCenterName;
    private Long eduCenterId;
    private String currentUserName;
    private Long currentUserId;
    private Long imgId;
}
