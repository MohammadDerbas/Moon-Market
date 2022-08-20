package com.example.demo.controller;

import com.example.demo.DTO.RegistrationRequest;
import com.example.demo.services.RegistrationServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.Principal;

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
    @GetMapping(path = "/verify")
    @JsonView(View.View1.class)

    public String confirm(Principal principal){
        return registrationServices.confirmToken(principal.getName());
    }







}
