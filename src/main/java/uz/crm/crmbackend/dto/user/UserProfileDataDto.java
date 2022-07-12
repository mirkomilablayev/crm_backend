package uz.crm.crmbackend.dto.user;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserProfilePhoto {
    private String fullName;
    private Long logoId;

}
