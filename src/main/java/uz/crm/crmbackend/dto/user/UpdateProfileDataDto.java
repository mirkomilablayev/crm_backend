package uz.crm.crmbackend.dto.user;


import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProfileDataDto {
    private String fullName;
    private String phoneNumber;
}
