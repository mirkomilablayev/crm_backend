package uz.crm.crmbackend.dto.user;


import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterStudent {
    private String fullName;
    private LocalDate birthDate;
    private String personalPhoneNumber;
    private String relativesPhoneNumber;
    private String username;
    private String password;
}
