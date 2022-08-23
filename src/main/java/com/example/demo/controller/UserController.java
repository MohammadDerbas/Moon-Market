package com.example.demo.controller;

import com.example.demo.services.UserServices;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }
    @GetMapping (value = "/disable")
    public void disableAccount(Principal principal){
        userServices.disableAccount(principal.getName());
    }
    @GetMapping (value = "/enable")
    public void enableAccount(@RequestParam("email")String email){userServices.enableUser(email);}
}
