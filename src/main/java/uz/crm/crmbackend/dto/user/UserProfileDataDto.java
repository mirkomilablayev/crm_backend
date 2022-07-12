package uz.crm.crmbackend.dto.user;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDataDto {
    private Long userId;
    private String fullName;
    private String phoneNumber;
    private Long logoId;

    private String username;
    private String password;
}
