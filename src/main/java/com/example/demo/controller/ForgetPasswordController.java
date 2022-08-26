package com.example.demo.controller;

import com.example.demo.entity.ConfirmationToken;
import com.example.demo.entity.EmailSender;
import com.example.demo.entity.User;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.UserRepo;
import com.example.demo.services.ConfirmationTokenService;
import com.example.demo.services.ForgetPasswordServices;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "forget_password")
public class ForgetPasswordController {
    private final ForgetPasswordServices forgetPasswordServices;
    private final UserRepo userRepo;
    private final EmailSender emailSender;
    private final ConfirmationTokenService confirmationTokenService;




    public ForgetPasswordController(ForgetPasswordServices forgetPasswordServices, UserRepo userRepo, EmailSender emailSender, ConfirmationTokenService confirmationTokenService) {
        this.forgetPasswordServices = forgetPasswordServices;
        this.userRepo = userRepo;
        this.emailSender = emailSender;
        this.confirmationTokenService = confirmationTokenService;
    }
    @PostMapping("/")
    public void sendEmail(@RequestParam("email")String email) throws MessagingException {
        forgetPasswordServices.sendEmail(email);
    }

    @GetMapping(path = "/confirm")
    public void confirm(@RequestParam("token") String token) throws IOException {
        forgetPasswordServices.confirmToken(token);
    }

    @PostMapping(path = "/change_password")
    public void changePassword(@RequestParam("token")String token,@RequestParam("password")String password,@RequestParam("passwordAgain")String passwordAgain){
        forgetPasswordServices.changePassword(token,password,passwordAgain);
    }

    @GetMapping(path="/enter_password")
    public void changePassword(@RequestParam("token")String token){

    }

}
