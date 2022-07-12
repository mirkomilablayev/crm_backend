package uz.crm.crmbackend.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import uz.crm.crmbackend.dto.user.LoginDto;
import uz.crm.crmbackend.dto.user.RegisterDto;
import uz.crm.crmbackend.entity.User;
import uz.crm.crmbackend.service.auth.AuthService;
import uz.crm.crmbackend.service.jwt.JwtProvider;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/registerAdmin")
    public HttpEntity<?> registerA(@RequestBody RegisterDto registerDto){
        return authService.registerA(registerDto);
    }

    @PostMapping("/registerTeacher")
    public HttpEntity<?> registerT(@RequestBody RegisterDto registerDto){
        return authService.registerT(registerDto);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
        User user = null;
        try{
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            user =(User) authenticate.getPrincipal();
        }catch (AuthenticationException e){
            return new ResponseEntity<>("", HttpStatus.BAD_GATEWAY);
        }
        String token = jwtProvider.generateToken(user.getUsername(), user.getUserRoleSet());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
