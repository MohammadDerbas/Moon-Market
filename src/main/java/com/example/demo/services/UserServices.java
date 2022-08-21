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

    public UserServices(UserRepo userRepo, RoleRepo roleRepo, ConfirmationTokenService confirmationTokenService, ImageProfilePicRepo imageProfilePicRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.confirmationTokenService = confirmationTokenService;
        this.imageProfilePicRepo = imageProfilePicRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         User user=userRepo.findUserByEmail(email)
                .orElseThrow(()->
        new UsernameNotFoundException(
                String.format(USER_NOT_FOUND_MSG, email)));

        if(user.getRoles().stream().findFirst().get().getName().equals("SELLER")) {
            UserDTO dto= new UserDTO(user.getId(), user.getEmail(),user.getPassword(),getAuthorities(Arrays.asList(roleRepo.findByName("SELLER"))),true,true,true,true);

            return dto;
        }
        if(user.getRoles().stream().findFirst().get().getName().equals("CUSTOMER")) {
            UserDTO dto= new UserDTO(user.getId(), user.getEmail(),user.getPassword(),getAuthorities(Arrays.asList(roleRepo.findByName("CUSTOMER"))),true,true,true,true);

            return dto;
        }
        if(user.getRoles().stream().findFirst().get().getName().equals("ADMIN")) {
            UserDTO dto= new UserDTO(user.getId(), user.getEmail(),user.getPassword(),getAuthorities(Arrays.asList(roleRepo.findByName("ADMIN"))),true,true,true,true);

            return dto;
        }
        return user;

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
        ImgProfilePic imgProfilePic=new ImgProfilePic(multipartFile.getOriginalFilename(),multipartFile.getContentType(), ImageUtility.compressImage(multipartFile.getBytes()),"http://localhost:8080/img/profile_pic/image/"+multipartFile.getOriginalFilename());
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
        ImgProfilePic imgProfilePic=new ImgProfilePic(multipartFile.getOriginalFilename(),multipartFile.getContentType(), ImageUtility.compressImage(multipartFile.getBytes()),"http://localhost:8080/img/profile_pic/image/"+multipartFile.getOriginalFilename());
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

    public void disableAccount(Long id) {
        userRepo.existsById(id);

    }
}
