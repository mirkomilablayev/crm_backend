package uz.crm.crmbackend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String fullName;
    private String passportSerialNumber;
    private String phoneNumber;
    private String password;
    private String username;
}

