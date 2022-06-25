package uz.crm.crmbackend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloWorldController {
    @GetMapping("/hello")
    public String getHello(){
        return "Hello World";
    }
}
