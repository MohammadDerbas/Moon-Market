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
            Privilege seller_read=new Privilege("SELLER_READ");
            Privilege seller_write=new Privilege("SELLER_WRITE");
            Privilege customer_read=new Privilege("CUSTOMER_READ");
            Privilege customer_write=new Privilege("CUSTOMER_WRITE");


            privilegeRepo.saveAll(List.of(seller_read,seller_write,customer_read,customer_write));
            List<Privilege> adminPrivileges = Arrays.asList(
                    seller_read, seller_write,customer_read,customer_write);
            List<Privilege> sellerPrivileges = Arrays.asList(
                    seller_read, seller_write,customer_read);
            List<Privilege> customerPrivileges = Arrays.asList(
                    seller_read, customer_read,customer_write);


            Role adminRole=new Role("ADMIN");
            adminRole.setPrivileges(adminPrivileges);
            Role sellerRole=new Role("SELLER");
            sellerRole.setPrivileges(sellerPrivileges);
            Role customerRole=new Role("CUSTOMER");
            customerRole.setPrivileges(customerPrivileges);
            roleRepo.saveAll(List.of(adminRole,sellerRole,customerRole));


            User admin=new Admin("Mohammad","Derbasco","1@gmail.com","password","Askar","0599599678","2555",List.of(adminRole));
            PasswordEncoder passwordEncoder=new PasswordEncoder();
            String encoded=passwordEncoder.bCryptPasswordEncoder().encode(admin.getPassword());
            admin.setPassword(encoded);
            admin.setEnabled(true);
            adminRepo.save(admin);



            Size small=new Size("small");
            Size medium=new Size("medium");
            Size large=new Size("large");
            Size x_large=new Size("x-large");
            Size xx_large=new Size("xx-large");
            Size XS=new Size("xs");
            Size S=new Size("s");
            Size M=new Size("m");
            Size L=new Size("l");
            Size XL=new Size("xl");
            Size XXL=new Size("xxl");
            Size XXXL=new Size("xxxl");
            Size S24=new Size("24");
            Size S25=new Size("25");
            Size S26=new Size("26");
            Size S27=new Size("27");
            Size S28=new Size("28");
            Size S29=new Size("29");
            Size S30=new Size("30");
            Size S31=new Size("31");
            Size S32=new Size("32");
            Size S33=new Size("33");
            Size S34=new Size("34");
            Size S35=new Size("35");
            Size S36=new Size("36");
            Size S37=new Size("37");
            Size S38=new Size("38");
            Size S39=new Size("39");
            Size S40=new Size("40");



            Type t_shirt=new Type("t-shirt") ;
            Type sweater=new Type("Sweater") ;
            Type dress=new Type("dress") ;
            Type shorts=new Type("Shorts") ;
            Type jeans=new Type("jeans") ;
            Type trouser=new Type("trouser") ;
            Type jacket=new Type("jacket") ;
            Type blazer=new Type("blazer") ;


            Brand hugo_boss=new Brand("Hugo Boss");
            Brand lacoste=new Brand("Lacoste");

            Category man_clothes=new Category("Man Clothes");
            Category woman_clothes=new Category("Woman Clothes");
            Category baby_clothes=new Category("Baby Clothes");




            List<Size>mix1=new ArrayList<>();
            List<Size>mix2=new ArrayList<>();

            mix1.add(x_large);
            mix1.add(small);
            mix1.add(xx_large);
            mix2.add(small);




            categoryRepo.saveAll(List.of(man_clothes,woman_clothes,baby_clothes));


            sizeRepo.saveAll(List.of(medium,small,large,x_large,xx_large,XS,S,M,L,XXL,XXXL,S24,S25,S26,S27,S28,S29,S30,S31,S32,S33,S34,S35,S36,S37,S38,S39,S40));

            typeRepo.saveAll(List.of(t_shirt,dress,shorts,sweater,jeans,trouser,jacket,blazer));

            brandRepo.saveAll(List.of(lacoste,hugo_boss));


            MemberShip memberShip1=new MemberShip("No-MemberShip");
            MemberShip memberShip2=new MemberShip("bronze");
            MemberShip memberShip3=new MemberShip("Silver");
            MemberShip memberShip4=new MemberShip("Golden");
            MemberShip memberShip5=new MemberShip("Premium");



            Prize prize1=new Prize("50$ gift",50);
            Prize prize2=new Prize("100$ gift",80);
            Prize prize3=new Prize("150$ gift",200);
            Prize prize4=new Prize("ticket to Turkey",500);
            prizeRepo.saveAll(List.of(prize1,prize2,prize3,prize4));



        };
    }
}
