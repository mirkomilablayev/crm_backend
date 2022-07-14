package uz.crm.crmbackend.dto.user;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRolesForToken {
    private Long id;
    private String name;
}
