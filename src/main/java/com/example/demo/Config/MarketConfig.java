package com.example.demo.Config;

import com.example.demo.entity.*;
import com.example.demo.repo.*;
import com.example.demo.security.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Configuration
public class MarketConfig {
    @Bean
    CommandLineRunner commandLineRunner(AdminRepo adminRepo,ProductRepo productRepo,RoleRepo roleRepo,PrivilegeRepo privilegeRepo, SizeRepo sizeRepo, TypeRepo typeRepo, ProductCommentRepo productCommentRepo, BrandRepo brandRepo, CustomerRepo customerRepo, MemberShipRepo memberShipRepo, PurchaseRepo purchaseRepo,  SellerRepo sellerRepo, OrderRepo orderRepo, CategoryRepo categoryRepo,  SellerCommentRepo sellerCommentRepo, FollowRepo followersRepo, StoreHouseRepo storeHouseRepo,ColorPropsRepo colorPropsRepo,LikeRepo likeRepo,ImageProfilePicRepo imageProfilePicRepo,PrizeRepo prizeRepo,PrizeChangerRepo prizeChangerRepo){
        return args -> {
System.out.println("server is listning os port 8080");

        };
    }
}
