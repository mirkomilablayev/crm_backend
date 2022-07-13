package uz.crm.crmbackend.exceptions;


import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException{
    private String msg;

}
