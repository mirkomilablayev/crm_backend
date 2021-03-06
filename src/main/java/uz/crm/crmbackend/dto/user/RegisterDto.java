package uz.crm.crmbackend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    private List<Long> subjectsId;
}

