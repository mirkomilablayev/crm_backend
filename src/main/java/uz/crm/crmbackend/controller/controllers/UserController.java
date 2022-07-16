package uz.crm.crmbackend.controller.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.crm.crmbackend.dto.user.UpdateProfileDataDto;
import uz.crm.crmbackend.service.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/saveUserLogo")
    public HttpEntity<?> saveFile(MultipartHttpServletRequest request) {
        return userService.saveFile(request);
    }

    @GetMapping("/getProfileData")
    public HttpEntity<?> getUserProfileData(){
        return userService.getProfileData();
    }

    @PutMapping("/updateSomeData")
    public HttpEntity<?> updateSomeUserData(@RequestBody UpdateProfileDataDto updateProfileDataDto){
        return userService.userUpdate(updateProfileDataDto);
    }

    @GetMapping
    public HttpEntity<?> getEduCenterStudents(){
        return userService.getEduCenterStudent();
    }


}
