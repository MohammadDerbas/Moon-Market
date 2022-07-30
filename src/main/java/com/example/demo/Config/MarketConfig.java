package com.example.demo.Config;

import com.example.demo.entity.*;
import com.example.demo.repo.*;
import com.example.demo.security.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MarketConfig {
    @Bean
    CommandLineRunner commandLineRunner(AdminRepo adminRepo,ProductRepo productRepo,RoleRepo roleRepo,PrivilegeRepo privilegeRepo, SizeRepo sizeRepo, TypeRepo typeRepo, ProductCommentRepo productCommentRepo, BrandRepo brandRepo, CustomerRepo customerRepo, MemberShipRepo memberShipRepo, PurchaseRepo purchaseRepo,  SellerRepo sellerRepo, OrderRepo orderRepo, CategoryRepo categoryRepo,  SellerCommentRepo sellerCommentRepo, FollowRepo followersRepo, StoreHouseRepo storeHouseRepo){
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

            Type t_shirt=new Type("T-shirt") ;
            Type sweater=new Type("Sweater") ;
            Type dress=new Type("Dress") ;
            Type shorts=new Type("Shorts") ;
            Type jeans=new Type("Jeans") ;

            Brand hugo_boss=new Brand("Hugo Boss");
            Brand lacoste=new Brand("Lacoste");

            Category man_clothes=new Category("Man Clothes");
            Category woman_clothes=new Category("Woman Clothes");
            Category baby_clothes=new Category("Baby Clothes");



            Product product=new Product("Cotton T-shirt",30,  0.50);
            Product product2=new Product("Cotton Trouser",100,.100);

            man_clothes.addProduct(product);
            woman_clothes.addProduct(product2);
            categoryRepo.saveAll(List.of(man_clothes,woman_clothes,baby_clothes));


            medium.addProduct(product);
            small.addProduct(product2);
            sizeRepo.saveAll(List.of(medium,small,large,x_large,xx_large));
            t_shirt.addProduct(product);
            jeans.addProduct(product2);
            typeRepo.saveAll(List.of(t_shirt,dress,shorts,sweater,jeans));
            lacoste.addProduct(product);
            hugo_boss.addProduct(product2);
            brandRepo.saveAll(List.of(lacoste,hugo_boss));

            product.buy();
            Product product1=new Product("white dress",20,2.0,small,dress,lacoste,woman_clothes);
            productRepo.saveAll(List.of(product,product1,product2));





            MemberShip memberShip1=new MemberShip("No-MemberShip");
            MemberShip memberShip2=new MemberShip("bronze");
            MemberShip memberShip3=new MemberShip("Silver");
            MemberShip memberShip4=new MemberShip("Golden");
            MemberShip memberShip5=new MemberShip("Premium");



            User user=new Seller("Mohammad",
                    "Derbas","mhammad_o_m@hotmail.com",
                    "momo0598134316","AskarCamp",
                    "0592215224","P4270413",List.of(sellerRole));
            String encoded1=passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encoded1);
            user.setEnabled(true);

            //user.addProduct(added1);





            sellerRepo.save(user);
            StoreHouse added1=new StoreHouse(new StoreHouseId(((Seller) user).getId(),product.getId()),(Seller) user,product);
            StoreHouse added2=new StoreHouse(new StoreHouseId(((Seller) user).getId(),product1.getId()),(Seller) user,product1);

            storeHouseRepo.saveAll(List.of(added1,added2));

            User Mohammad=new Customer("Wajeeh",
                    "Salem","wajeehsalem@hotmail.com",
                    "momo654321","Jenin",
                    "0598654321","P4270413",List.of(customerRole));
            String encoded2=passwordEncoder.bCryptPasswordEncoder().encode(Mohammad.getPassword());
            Mohammad.setPassword(encoded2);
            Mohammad.setEnabled(true);




            memberShip5.addCustomer((Customer) Mohammad);
            memberShipRepo.saveAll(List.of(memberShip1,memberShip2,memberShip3,memberShip4,memberShip5));
            //Mohammad.addPurchase(new Purchase(new PurchaseId(1l,1l),Mohammad,product));
            Purchase purchase=new Purchase(3l,1l, LocalDate.of(2022,07,29),2.5,5);
            Purchase purchase2=new Purchase(3l,1l, LocalDate.of(2022,06,29),3.0,6);
            Purchase purchase3=new Purchase(3l,1l, LocalDate.of(2022,06,24),4.5,9);
            Purchase purchase4=new Purchase(3l,1l, LocalDate.of(2021,06,02),5.0,10);
            Purchase purchase5=new Purchase(3l,1l, LocalDate.of(2021,06,24),2.0,4);
            ((Customer) Mohammad).setCustomerPurchases(17.0);



            purchaseRepo.saveAll(List.of(purchase,purchase2,purchase3,purchase4,purchase5));
            /*System.out.println(purchase.getId());*/
            System.out.println(product.getId());
            Order order=new Order(new OrderId(1L,1L), (Customer) Mohammad,product,2);
            orderRepo.save(order);
            Mohammad.addToCart(order);
           /* Mohammad.addPurchase(purchase);
            Mohammad.addPurchase(purchase2);*/
            Followers followers=new Followers(new FollowersId(((Customer) Mohammad).getId(),((Seller) user).getId()), (Customer) Mohammad, (Seller) user);
            followersRepo.save(followers);
            Mohammad.follow(followers);


            customerRepo.save(Mohammad);
            ProductComment productComment=new ProductComment("Wow , beautiful t-shirt i will come with my friend to buy it");
            productComment.setCustomer((Customer) Mohammad);
            productComment.setProduct(product);
            productCommentRepo.save(productComment);
            SellerComment sellerComment=new SellerComment("good Seller I advice to buy from him");
            sellerComment.setSeller((Seller) user);
            sellerComment.setCustomer((Customer) Mohammad);
            sellerCommentRepo.save(sellerComment);












        };
    }
}
