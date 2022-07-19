package com.example.demo.Config;

import com.example.demo.entity.*;
import com.example.demo.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MarketConfig {
    @Bean
    CommandLineRunner commandLineRunner(ProductRepo productRepo, SizeRepo sizeRepo, TypeRepo typeRepo, ProductCommentRepo productCommentRepo, BrandRepo brandRepo,CustomerRepo customerRepo,MemberShipRepo memberShipRepo,PurchaseRepo purchaseRepo,RoleRepo roleRepo,AuthorityRepo authorityRepo,SellerRepo sellerRepo,OrderRepo orderRepo,CategoryRepo categoryRepo,AdminRepo adminRepo,DeliveryManRepo deliveryManRepo,SellerCommentRepo sellerCommentRepo,FollowersRepo followersRepo,StoreHouseRepo storeHouseRepo){
        return args -> {





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



            Product product=new Product("Cotton T-shirt",30,50);
            Product product2=new Product("Cotton Trouser",100,100);

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
            Product product1=new Product("white dress",20,1500,small,dress,lacoste,woman_clothes);
            productRepo.saveAll(List.of(product,product1,product2));





            MemberShip memberShip1=new MemberShip("No_MemberShip");
            MemberShip memberShip2=new MemberShip("bronze");
            MemberShip memberShip3=new MemberShip("Silver");
            MemberShip memberShip4=new MemberShip("Golden");
            MemberShip memberShip5=new MemberShip("Premium");

            Role role =new Role("Admin");
            Role role1 =new Role("Seller");
            Role role2 =new Role("Customer");
            Role role3 =new Role("DeliveryMan");
            Authority authority=new Authority("Write");
            Authority authority1=new Authority("Read");
            Authority authority2=new Authority("ReadWrite");
            role1.addAuthority(authority);
            role2.addAuthority(authority1);
            role.addAuthority(authority2);
            roleRepo.saveAll(List.of(role1,role2,role3,role));

            User user=new Seller("MohammadDerbas","Mohammad",
                    "Derbas","mhammad_o_m@hotmail.com",
                    "momo0598134316","AskarCamp",
                    "0592215224","P4270413");
            User user1=new Admin("HasanHaq","Hasan",
                    "Haq","HasanHaq@hotmail.com",
                    "123456789","Nablus",
                    "0598123456","P4270413");

            //user.addProduct(added1);




            user1.setRole(role);
            user.setRole(role1);
            sellerRepo.save(user);
            StoreHouse added1=new StoreHouse(new StoreHouseId(((Seller) user).getId(),product.getId()),(Seller) user,product);
            StoreHouse added2=new StoreHouse(new StoreHouseId(((Seller) user).getId(),product1.getId()),(Seller) user,product1);

            storeHouseRepo.saveAll(List.of(added1,added2));
            adminRepo.save(user1);

            User Mohammad=new Customer("WajeehSalem","Wajeeh",
                    "Salem","wajeehsalem@hotmail.com",
                    "momo654321","Jenin",
                    "0598654321","P4270413");


            Mohammad.setRole(role2);


            memberShip5.addCustomer((Customer) Mohammad);
            memberShipRepo.saveAll(List.of(memberShip1,memberShip2,memberShip3,memberShip4,memberShip5));
            //Mohammad.addPurchase(new Purchase(new PurchaseId(1l,1l),Mohammad,product));
            Purchase purchase=new Purchase(new PurchaseId(1L,1L), (Customer) Mohammad,product);
            Purchase purchase2=new Purchase(new PurchaseId(1L,2L), (Customer) Mohammad,product2);

            purchaseRepo.saveAll(List.of(purchase,purchase2));
            System.out.println(purchase.getId());
            System.out.println(product.getId());
            Order order=new Order(new OrderId(1L,1L), (Customer) Mohammad,product);
            orderRepo.save(order);
            Mohammad.addToCart(order);
            Mohammad.addPurchase(purchase);
            Mohammad.addPurchase(purchase2);
            Followers followers=new Followers(new FollowersId(((Customer) Mohammad).getId(),((Seller) user).getId()), (Customer) Mohammad, (Seller) user);
            followersRepo.save(followers);
            Mohammad.follow(followers);


            customerRepo.save(Mohammad);
            ProductComment productComment=new ProductComment(new ProductCommentId(((Customer)Mohammad).getId(),product.getId()), (Customer) Mohammad,product,"Wow , beautiful one i will come with my friend to buy it");
            Mohammad.addProductComment(productComment);
            productCommentRepo.save(productComment);

            SellerComment sellerComment=new SellerComment(new SellerCommentId(((Customer) Mohammad).getId(),((Seller) user).getId()), (Customer) Mohammad, (Seller) user,"Good Seller, I advict to buy from him");
            sellerCommentRepo.save(sellerComment);












        };
    }
}
