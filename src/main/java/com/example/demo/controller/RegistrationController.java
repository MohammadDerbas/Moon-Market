package com.example.demo.controller;

import com.example.demo.DTO.RegistrationRequest;
import com.example.demo.services.RegistrationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping(path="registration")
public class RegistrationController {
    private final RegistrationServices registrationServices;
    @Autowired
    public RegistrationController(RegistrationServices registrationServices) {
        this.registrationServices = registrationServices;
    }
    @PostMapping("/")
    public String register(@RequestBody RegistrationRequest request) throws MessagingException {
    return registrationServices.rigister(request);
    }
    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token){
    return registrationServices.confirmToken(token);
    }



}
