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
    CommandLineRunner commandLineRunner(AdminRepo adminRepo,ProductRepo productRepo,RoleRepo roleRepo,PrivilegeRepo privilegeRepo, SizeRepo sizeRepo, TypeRepo typeRepo, ProductCommentRepo productCommentRepo, BrandRepo brandRepo, CustomerRepo customerRepo, MemberShipRepo memberShipRepo, PurchaseRepo purchaseRepo,  SellerRepo sellerRepo, OrderRepo orderRepo, CategoryRepo categoryRepo,  SellerCommentRepo sellerCommentRepo, FollowRepo followersRepo, StoreHouseRepo storeHouseRepo,ImagesRepo imagesRepo,ColorPropsRepo colorPropsRepo,LikeRepo likeRepo,ImageProfilePicRepo imageProfilePicRepo){
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


            ImgProfilePic imgProfilePicAdmin=new ImgProfilePic();
            User admin=new Admin("Mohammad","Derbasco","1@gmail.com","password","Askar","0599599678","2555",List.of(adminRole));
            imgProfilePicAdmin.setUser(admin);
            PasswordEncoder passwordEncoder=new PasswordEncoder();
            String encoded=passwordEncoder.bCryptPasswordEncoder().encode(admin.getPassword());
            admin.setPassword(encoded);
            admin.setEnabled(true);
            adminRepo.save(admin);
            imageProfilePicRepo.save(imgProfilePicAdmin);

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



            Product product=new Product("Cotton T-shirt",30,  0.50);
            Product product2=new Product("Cotton Trouser",100,.100);
            List<Size>mix1=new ArrayList<>();
            List<Size>mix2=new ArrayList<>();

            mix1.add(x_large);
            mix1.add(small);
            mix1.add(xx_large);
            mix2.add(small);





            man_clothes.addProduct(product);
            woman_clothes.addProduct(product2);
            categoryRepo.saveAll(List.of(man_clothes,woman_clothes,baby_clothes));

            mix1.stream().forEach(size -> size.addProduct(product));
            mix2.stream().forEach(size -> size.addProduct(product2));
            sizeRepo.saveAll(List.of(medium,small,large,x_large,xx_large,XS,S,M,L,XXL,XXXL,S24,S25,S26,S27,S28,S29,S30,S31,S32,S33,S34,S35,S36,S37,S38,S39,S40));
            System.out.println(x_large.getProducts());
            System.out.println("888888888888888888888");
            t_shirt.addProduct(product);
            jeans.addProduct(product2);
            typeRepo.saveAll(List.of(t_shirt,dress,shorts,sweater,jeans,trouser,jacket,blazer));
            lacoste.addProduct(product);
            hugo_boss.addProduct(product2);
            brandRepo.saveAll(List.of(lacoste,hugo_boss));

            product.buy();
            Product product1=new Product("white dress",20,2.0,List.of(small,large,medium),dress,lacoste,woman_clothes);
            productRepo.saveAll(List.of(product,product1,product2));

            ColorProps colorProps1=new ColorProps("Blue");
            colorProps1.setProduct(product);
            ColorProps colorProps2=new ColorProps("Red");
            colorProps2.setProduct(product);
            ColorProps colorProps3=new ColorProps("Blue");
            colorProps3.setProduct(product2);
            ColorProps colorProps4=new ColorProps("Red");
            colorProps4.setProduct(product2);
            ColorProps colorProps5=new ColorProps("Red");
            colorProps5.setProduct(product1);
            ColorProps colorProps6=new ColorProps("Green");
            colorProps6.setProduct(product1);

            ColorProps colorProps7=new ColorProps("Yellow");
            colorProps7.setProduct(product1);

            colorPropsRepo.saveAll(List.of(colorProps1,colorProps2,colorProps3,colorProps4,colorProps5,colorProps6,colorProps7));
            Images images1=new Images("https://static.zara.net/photos///2022/I/0/1/p/8246/241/400/2/w/480/8246241400_6_1_1.jpg?ts=1654586460306");
            images1.setColorProps(colorProps1);
            Images images2=new Images("https://static.zara.net/photos///2022/I/0/1/p/8246/243/526/2/w/385/8246243526_6_1_1.jpg?ts=1654586461253");
            images2.setColorProps(colorProps1);
            Images images3=new Images("https://static.zara.net/photos///2022/V/0/1/p/6045/023/406/2/w/385/6045023406_6_1_1.jpg?ts=1642498964106");
            images3.setColorProps(colorProps2);

            Images images5=new Images("https://static.zara.net/photos///2022/I/0/1/p/6840/089/406/2/w/378/6840089406_6_1_1.jpg?ts=1658413489985");
            images5.setColorProps(colorProps3);
            Images images7=new Images("https://static.zara.net/photos///2022/I/2/1/p/4550/512/999/2/w/378/4550512999_6_1_1.jpg?ts=1657273374505");
            images7.setColorProps(colorProps4);



            imagesRepo.saveAll(List.of(images1,images2,images3,images5,images7));




            MemberShip memberShip1=new MemberShip("No-MemberShip");
            MemberShip memberShip2=new MemberShip("bronze");
            MemberShip memberShip3=new MemberShip("Silver");
            MemberShip memberShip4=new MemberShip("Golden");
            MemberShip memberShip5=new MemberShip("Premium");


            ImgProfilePic imgProfilePicSeller=new ImgProfilePic();
            User user=new Seller("Mohammad",
                    "Derbas","mhammad_o_m@hotmail.com",
                    "123456","AskarCamp",
                    "0592215224","P4270413",List.of(sellerRole));
            imgProfilePicSeller.setUser(user);

            String encoded1=passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword());
            user.setPassword(encoded1);
            user.setEnabled(true);

            //user.addProduct(added1);





            sellerRepo.save(user);
            imageProfilePicRepo.save(imgProfilePicSeller);
            StoreHouse added1=new StoreHouse(new StoreHouseId(((Seller) user).getId(),product.getId()),(Seller) user,product);
            StoreHouse added2=new StoreHouse(new StoreHouseId(((Seller) user).getId(),product1.getId()),(Seller) user,product1);

            storeHouseRepo.saveAll(List.of(added1,added2));
            ImgProfilePic imgProfilePicCustomer=new ImgProfilePic();
            User Mohammad=new Customer("Wajeeh",
                    "Salem","wajeehsalem@hotmail.com",
                    "momo654321","Jenin",
                    "0598654321","P4270413",List.of(customerRole));
            imgProfilePicCustomer.setUser(Mohammad);
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
            imageProfilePicRepo.save(imgProfilePicCustomer);

            Like like=new Like(new LikeId(Mohammad.getId(),product.getId()), (Customer) Mohammad,product);
            likeRepo.save(like);
            ProductComment productComment=new ProductComment("Wow , beautiful t-shirt i will come with my friend to buy it");
            productComment.setCustomer((Customer) Mohammad);
            productComment.setProduct(product);
            productCommentRepo.save(productComment);
            SellerComment sellerComment=new SellerComment("good Seller I advice to buy from him",4.0);
            sellerComment.setSeller((Seller) user);
            sellerComment.setCustomer((Customer) Mohammad);
            sellerCommentRepo.save(sellerComment);












        };
    }
}
