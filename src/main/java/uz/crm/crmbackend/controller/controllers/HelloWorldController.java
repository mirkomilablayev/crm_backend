package uz.crm.crmbackend.controller.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/hello")
@RequiredArgsConstructor
public class HelloWorldController {
    @GetMapping
    public String getHello(){
        return "Hello World";
    }
}
