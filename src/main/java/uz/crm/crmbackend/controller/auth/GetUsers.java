package uz.crm.crmbackend.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.crm.crmbackend.repository.user.UserRepo;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class GetUsers {

    private final UserRepo userRepo;

    @GetMapping
    public HttpEntity<?> getStudent(){
        return ResponseEntity.status(HttpStatus.OK).body(userRepo.findAll());
    }

}
