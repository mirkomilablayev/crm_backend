package uz.crm.crmbackend.controller.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.crm.crmbackend.service.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/saveUserLogo")
    public HttpEntity<?> saveFile(MultipartHttpServletRequest request){
        return userService.saveFile(request);
    }
}
