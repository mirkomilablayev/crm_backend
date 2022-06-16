package uz.crm.crmbackend.tools.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UsernameAlreadyRegisterException extends RuntimeException{
    private String msg;
}
