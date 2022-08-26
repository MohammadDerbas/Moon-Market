package com.example.demo.controller;

import com.example.demo.DTO.RegistrationRequest;
import com.example.demo.services.RegistrationServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
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
    public String register(@RequestParam("firstName")String firstName, @RequestParam("lastName")String lastName, @RequestParam("email")String email, @RequestParam("password")String password, @RequestParam("address")String address, @RequestParam("phone")String phone, @RequestParam("postalcode")String postalcode,@RequestParam("isCustomer")Boolean isCustomer,@RequestParam("isSeller")Boolean isSeller, @RequestParam("image")MultipartFile multipartFile) throws MessagingException, IOException {
    return registrationServices.rigister(firstName,lastName,email,password,address,phone,postalcode,isCustomer,isSeller,multipartFile);
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


    @PostMapping("/login")
    public void login(@RequestParam String email,@RequestParam String password){

    }




}
