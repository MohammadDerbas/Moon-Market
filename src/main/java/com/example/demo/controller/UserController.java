package com.example.demo.controller;

import com.example.demo.services.UserServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    public void disableAccount(Long id){
        userServices.disableAccount(id);
    }
}
