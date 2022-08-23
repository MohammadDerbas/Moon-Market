package com.example.demo.services;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Util.ImageUtility;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.ImageProfilePicRepo;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.security.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServices implements UserDetailsService {
    private static final String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    ;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final ConfirmationTokenService confirmationTokenService;
    private final ImageProfilePicRepo imageProfilePicRepo;
    private final EmailSender emailSender;


    public UserServices(UserRepo userRepo, RoleRepo roleRepo, ConfirmationTokenService confirmationTokenService, ImageProfilePicRepo imageProfilePicRepo, EmailSender emailSender) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.confirmationTokenService = confirmationTokenService;
        this.imageProfilePicRepo = imageProfilePicRepo;
        this.emailSender = emailSender;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user=userRepo.findUserByEmail(email)
                .orElseThrow(()->
        new UsernameNotFoundException(
                String.format(USER_NOT_FOUND_MSG, email)));

         if(user.isEnabled()) {

             if (user.getRoles().stream().findFirst().get().getName().equals("SELLER")) {
                 UserDTO dto = new UserDTO(user.getId(), user.getEmail(), user.getPassword(), getAuthorities(Arrays.asList(roleRepo.findByName("SELLER"))), true, true, true, true);

                 return dto;
             }
             if (user.getRoles().stream().findFirst().get().getName().equals("CUSTOMER")) {
                 UserDTO dto = new UserDTO(user.getId(), user.getEmail(), user.getPassword(), getAuthorities(Arrays.asList(roleRepo.findByName("CUSTOMER"))), true, true, true, true);

                 return dto;
             }
             if (user.getRoles().stream().findFirst().get().getName().equals("ADMIN")) {
                 UserDTO dto = new UserDTO(user.getId(), user.getEmail(), user.getPassword(), getAuthorities(Arrays.asList(roleRepo.findByName("ADMIN"))), true, true, true, true);

                 return dto;
             }
             return user;
         }
        else{
            String link="http://localhost:8080/user/enable?email="+email;
             try {
                 emailSender.send(email,buildEmail(user.getFirstName(),link),"enable");
             } catch (MessagingException e) {
                 e.printStackTrace();
             }
             throw new UsernameNotFoundException("username not found");
         }
    }



    public String signUpUser(Customer customer, MultipartFile multipartFile) throws IOException {
        boolean userExists = userRepo.findUserByEmail(customer.getEmail())
                .isPresent();
        if(userExists){
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new ApiRequestException("email already taken");
        }
        PasswordEncoder passwordEncoder=new PasswordEncoder();
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        userRepo.save(customer);
        ImgProfilePic imgProfilePic=new ImgProfilePic(multipartFile.getOriginalFilename(),multipartFile.getContentType(), ImageUtility.compressImage(multipartFile.getBytes()),"http://localhost:8080/img/get/profile_pic/"+multipartFile.getOriginalFilename());
        imgProfilePic.setUser(customer);
        imageProfilePicRepo.save(imgProfilePic);
        String token= UUID.randomUUID().toString();
        ConfirmationToken confirmationToken=new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                customer
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;

    }
    public String signUpUser(Seller seller,MultipartFile multipartFile) throws IOException {
        boolean userExists = userRepo.findUserByEmail(seller.getEmail())
                .isPresent();
        if(userExists){
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.

            throw new ApiRequestException("email already taken");
        }
        PasswordEncoder passwordEncoder=new PasswordEncoder();
        String encodedPassword = passwordEncoder.bCryptPasswordEncoder().encode(seller.getPassword());
        seller.setPassword(encodedPassword);
        userRepo.save(seller);
        ImgProfilePic imgProfilePic=new ImgProfilePic(multipartFile.getOriginalFilename(),multipartFile.getContentType(), ImageUtility.compressImage(multipartFile.getBytes()),"http://localhost:8080/img/get/profile_pic/"+multipartFile.getOriginalFilename());
        imgProfilePic.setUser(seller);
        imageProfilePicRepo.save(imgProfilePic);
        String token= UUID.randomUUID().toString();
        ConfirmationToken confirmationToken=new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                seller
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;

    }

    public void enableUser(String email) {
       userRepo.enableAppUser(email);
    }



    public Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));

    }


    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
    public void forgetPassword(String email){

    }

    public void disableAccount(String email) {

        userRepo.disableAppUser(email);
    }
    private String buildEmail(String firstName, String link) {
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
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Enable Your Account</span>\n" +
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
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + firstName + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> We are happy to see you again. Please click on the below link to enable your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Enable Account</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
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
