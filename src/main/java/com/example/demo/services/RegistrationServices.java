package com.example.demo.services;

import com.example.demo.Util.ImageUtility;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.MemberShipRepo;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.*;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class RegistrationServices {
    private final UserServices userServices;
    private final EmailValidator emailValidator;
    private final MemberShipRepo memberShipRepo;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final ImageProfilePicRepo imageProfilePicRepo;


    public RegistrationServices(UserServices userServices, EmailValidator emailValidator, MemberShipRepo memberShipRepo, ConfirmationTokenService confirmationTokenService, EmailSender emailSender, RoleRepo roleRepo, UserRepo userRepo, ImageProfilePicRepo imageProfilePicRepo) {
        this.userServices = userServices;
        this.emailValidator = emailValidator;
        this.memberShipRepo = memberShipRepo;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSender = emailSender;
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.imageProfilePicRepo = imageProfilePicRepo;
    }

    public String rigister(String firstName, String lastName, String email, String password, String address, String phone, String postalcode,Boolean isCustomer,Boolean isSeller, MultipartFile multipartFile) throws MessagingException, IOException {
       boolean isValidEmail = emailValidator.test(email);
        if(!isValidEmail){
        throw new ApiRequestException("email not valid");
        }

        if(isCustomer) {
            Role role=roleRepo.findByName("CUSTOMER");

            Customer popo = new Customer(
                    firstName,
                    lastName,
                    email,
                    password,
                    address,
                    phone,
                    postalcode,
                    Arrays.asList(role));



            memberShipRepo.findByType("No-MemberShip").addCustomer(popo);


            String token = userServices.signUpUser(
                    popo,
                    multipartFile
            );
            String link="http://localhost:8080/registration/confirm?token="+token;
            emailSender.send(email,buildEmail(firstName,link),"s");

            return token;
        }
        if(isSeller){
            Role role=roleRepo.findByName("SELLER");
            Seller seller = new Seller(
                    firstName,
                    lastName,
                    email,
                    password,
                    address,
                    phone,
                    postalcode,
                    Arrays.asList(role)
            );

            String token = userServices.signUpUser(
                    seller,
                    multipartFile
            );
            System.out.println(seller.getAuthorities()+"999999999999999999999999999999999999999999999");
            String link="http://localhost:8080/registration/confirm?token="+token;
            emailSender.send(email,buildEmail(firstName,link),"s");


            return token;

        }
        return "not-reachable";

    }

    public String confirmToken(String token) {
        System.out.println(token);
        ConfirmationToken confirmationToken=confirmationTokenService
                .getToken(token)
                .orElseThrow(()->
                        new ApiRequestException("token not found"));
            if(confirmationToken.getConfirmedAt()!=null){
                throw new ApiRequestException("email already confirmed");
            }
        LocalDateTime expiresAt = confirmationToken.getExpiresAt();
        if(expiresAt.isBefore(LocalDateTime.now())){
            throw new ApiRequestException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        userServices.enableUser(
                confirmationToken.getUser().getEmail()
        );
       User user= confirmationToken.getUser();
        return "confirmed";

    }

    public User verifyUser(String name){
        User seller = userRepo.findUserByEmail(name).get();

        return seller;


    }
    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
