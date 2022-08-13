package com.example.demo.services;

import com.example.demo.DTO.ColorPropsDTO;
import com.example.demo.DTO.CommentFromDto;
import com.example.demo.DTO.ImageDto;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerServices {
    private final SellerRepo sellerRepo;
    private final UserRepo userRepo;
    private final StoreHouseRepo storeHouseRepo;
    private final ProductRepo productRepo;
    private final BrandRepo brandRepo;
    private final TypeRepo typeRepo;
    private final CategoryRepo categoryRepo;
    private final SizeRepo sizeRepo;
    private final SellerCommentRepo sellerCommentRepo;
    private final FollowRepo followRepo;
    private final ColorPropsRepo colorPropsRepo;
    private final ImagesRepo imagesRepo;
    private final CustomerRepo customerRepo;
    private final LikeRepo likeRepo;
    @Autowired
    public SellerServices(SellerRepo sellerRepo, @Qualifier("sellerRepo") UserRepo userRepo, StoreHouseRepo storeHouseRepo, ProductRepo productRepo, BrandRepo brandRepo, TypeRepo typeRepo, CategoryRepo categoryRepo, SizeRepo sizeRepo, SellerCommentRepo sellerCommentRepo, FollowRepo followRepo, ColorPropsRepo colorPropsRepo, ImagesRepo imagesRepo, CustomerRepo customerRepo, LikeRepo likeRepo) {
        this.sellerRepo = sellerRepo;
        this.userRepo = userRepo;
        this.storeHouseRepo = storeHouseRepo;
        this.productRepo = productRepo;
        this.brandRepo = brandRepo;
        this.typeRepo = typeRepo;
        this.categoryRepo = categoryRepo;
        this.sizeRepo = sizeRepo;
        this.sellerCommentRepo = sellerCommentRepo;
        this.followRepo = followRepo;
        this.colorPropsRepo = colorPropsRepo;
        this.imagesRepo = imagesRepo;
        this.customerRepo = customerRepo;
        this.likeRepo = likeRepo;
    }

    public Product getInfoSellerProductWithId(Long id, Long id2,Boolean like,String email) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        boolean productExists=productRepo.existsById(id2);
        if (!productExists) {
            throw new ApiRequestException("Product with id " + id2 + "does not exist");
        }
        if(like!=null) {
            Long id3 = customerRepo.findUserByEmail(email).get().getId();
            Like like1 = new Like(new LikeId(id3, id2), customerRepo.findUserByID(id3).get(), productRepo.findProductById(id2));
            if (like) {
                likeRepo.save(like1);
            }
            else{
                likeRepo.delete(like1);
            }
        }
        return productRepo.getReferenceById(id2);
    }

    public Optional<Seller> getSeller(Long id,Boolean follow,String email) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        Optional<Seller> seller = sellerRepo.findUserById(id);
        boolean exist1=customerRepo.existsByEmail(email);
        if(!exist1){
            throw new ApiRequestException("He is not a customer try to enter with a customer account");

        }
        Customer customer= (Customer) customerRepo.findUserByEmail(email).get();
        if(follow!=null){
            Followers followers=new Followers(new FollowersId(customer.getId(),id),customer,seller.get());
            if(follow){
                followRepo.save(followers);
            }
            else{
                if(!followRepo.isCustmoerFollowSeller(customer.getId(),id)){
                    throw new ApiRequestException("customer with id "+customer.getId()+"did not follow seller with id"+id);

                }
                else{
                    followRepo.delete(followers);
                }
            }
        }
        return seller;

    }

    public void updateSellerInfo(Long id,  String firstName, String lastName, String email, String password, String address, String phone, String postalCode) {
        boolean exists = userRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        User seller = userRepo.getReferenceById(id);

        if (email != null && email.length() > 0 && !Objects.equals(seller.getEmail(), email)) {
            Optional<User> studentOptional = userRepo.findUserByEmail(email);
            if (studentOptional.isPresent()) {
                throw new ApiRequestException("email taken");
            }
            seller.setEmail(email);
        }
        if (firstName != null && firstName.length() > 0 && !Objects.equals(seller.getFirstName(), firstName)) {

            seller.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(seller.getLastName(), lastName)) {

            seller.setLastName(lastName);
        }
        if (address != null && address.length() > 0 && !Objects.equals(seller.getAddress(), address)) {

            seller.setAddress(address);
        }
        if (phone != null && phone.length() > 0 && !Objects.equals(seller.getPhone(), phone)) {

            seller.setPhone(phone);
        }
        if (postalCode != null && postalCode.length() > 0 && !Objects.equals(seller.getPostalCode(), postalCode)) {

            seller.setPostalCode(postalCode);
        }
        if (password != null && password.length() > 0 && !Objects.equals(seller.getPassword(), password)) {

            seller.setPassword(password);
        }

        userRepo.save(seller);
    }

    public void addNewProduct(Long id, @NotNull Product product) {
        boolean exists=brandRepo.existsByBrand(product.getBrand().getBrand());
        brandRepo.findByBrand(product.getBrand().getBrand()).addProduct(product);
        if(!exists)
            throw new ApiRequestException("this brand does not exist");
        boolean exists1=typeRepo.existsByType(product.getType().getType());
        typeRepo.findByType(product.getType().getType()).addProduct(product);
        if(!exists1)
            throw new ApiRequestException("this type does not exist");
        List <String> s=new ArrayList<>();

        product.getSizes().stream().forEach(size -> s.add(size.getSize()));
        boolean exists2=sizeRepo.existsBySizeIn(s);

        List <Size> sizes2=sizeRepo.findBySize(s);
        System.out.println(sizes2+"66666666666666666666");
        ArrayList<Size>ss=new ArrayList<>();
        product.setSizes(ss);

           sizeRepo.findBySize(s).forEach(size -> size.addProduct(product));//add product
        System.out.println(sizeRepo.findBySize(s)+"888888888888");

        if(!exists2)
            throw new ApiRequestException("this size does not exist");
        boolean exists3=categoryRepo.existsByCategory(product.getCategory().getCategory());
        categoryRepo.findByCategory(product.getCategory().getCategory()).addProduct(product);
        if(!exists3)
            throw new ApiRequestException("this category does not exist");


        productRepo.save(product);
        product.getColorProps();
        for (ColorProps colorProps:product.getColorProps()
             ) {
                colorProps.setProduct(product);
                colorPropsRepo.save(colorProps);
            for (Images images:colorProps.getImages()
                 ) {
                images.setColorProps(colorProps);
                imagesRepo.save(images);
            }
        }

        //User seller=  sellerRepo.getReferenceById(id);
        Optional<Seller> seller = sellerRepo.findUserById(id);
        StoreHouse op = new StoreHouse(new StoreHouseId(id, product.getId()), seller.get(), product);
        storeHouseRepo.save(op);
    }

    public List<Product> showProduct(Long id,Long productId,Boolean like,String email) {
        if (productId != null) {
            boolean exist = productRepo.existsById(productId);
            if (!exist) {
                throw new ApiRequestException("product with id" + productId + "does not exist");
            }
        }
        long id2 = userRepo.findUserByEmail(email).get().getId();

        boolean exist2 = customerRepo.existsById(id2);
        if (!exist2) {
            throw new ApiRequestException("He  is not a customer try with customer id ");

        }
        if (like != null) {
            Like like1 = new Like(new LikeId(id2, productId), customerRepo.findUserByID(id2).get(), productRepo.findProductById(productId));
            if (like) {
                likeRepo.save(like1);
            } else {
                if (!likeRepo.isCustmoerLikeProduct(id2, productId)) {
                    throw new ApiRequestException("customer with id +" + id2 + " did not like product with id" + id);

                }
                likeRepo.delete(like1);

            }
        }
        return productRepo.showProductWithSpecificSeller(id);

    }

    public List<User> showSellers(Long sellerId,Boolean follow,String email) {

        boolean exists = sellerRepo.existsById(sellerId);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + sellerId + "does not exist");
        }
        Optional<Seller> seller = sellerRepo.findUserById(sellerId);
        boolean exist1=customerRepo.existsByEmail(email);
        if(!exist1){
            throw new ApiRequestException("He is not a customer try to enter with a customer account");

        }
        Customer customer= (Customer) customerRepo.findUserByEmail(email).get();
        if(follow!=null){
            Followers followers=new Followers(new FollowersId(customer.getId(),sellerId),customer,seller.get());
            if(follow){
                followRepo.save(followers);
            }
            else{
                if(!followRepo.isCustmoerFollowSeller(customer.getId(),sellerId)){
                    throw new ApiRequestException("customer with id "+customer.getId()+"did not follow seller with id"+sellerId);

                }
                else{
                    followRepo.delete(followers);
                }
            }
        }
        List<User> sellers = sellerRepo.findAll();
        return sellers;
    }

    public void updateSellerProductWithId(Long id, Long id2, String description, Integer quantity, Double price, List<String> size, String type, String brand, String category) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        boolean productExists=productRepo.existsById(id2);
        if (!productExists) {
            throw new ApiRequestException("Product with id " + id2 + "does not exist");
        }
        Product product = productRepo.getReferenceById(id2);

        if (description != null && description.length() > 0 && !Objects.equals(product.getDescription(), description)) {

            product.setDescription(description);
        }
        if (price != null) {
            if (price < 0) {
                throw new ApiRequestException("price can't be negative number");
            }
            else if (price == 0) {
                throw new ApiRequestException("price can't be zero");
            }
            else  {
                product.setPrice(price);
            }

        }
        if (quantity != null) {
            if (quantity < 0) {
                throw new ApiRequestException("quantity can not be negative number");
            }
            else  {
                product.setQuantity(quantity);
            }

        }
        if (size != null  ) {


            boolean exists2=sizeRepo.existsBySizeIn(size);
            if (!exists2) {
                throw new ApiRequestException("one of the sizes"+size+"does not exist");

            }

            ArrayList<Size>ss=new ArrayList<>();
            product.setSizes(ss);

            sizeRepo.findBySize(size).forEach(size3 -> size3.addProduct(product));



        }
        if (type != null && type.length() > 0 ) {
            boolean exist=typeRepo.existsByType(type);
            if(!exist){
                throw new ApiRequestException("type"+type+"does not exist");

            }

            product.setType(typeRepo.findByType(type));
        }
        if (brand != null && brand.length() > 0 ) {
            boolean exist=brandRepo.existsByBrand(brand);
            if(!exist){
                throw new ApiRequestException("brand"+brand+"does not exist");

            }

            product.setBrand(brandRepo.findByBrand(brand));
        }
        if (category != null && category.length() > 0 ) {
            boolean exist=categoryRepo.existsByCategory(category);
            if(!exist){
                throw new ApiRequestException("category"+category+"does not exist");

            }

            product.setCategory(categoryRepo.findByCategory(category));
        }
        productRepo.save(product);

    }

    public void deleteProduct(Long id, Long id2) {
        boolean exist=sellerRepo.existsById(id);
        if(!exist){
            throw new ApiRequestException("seller with id"+id+"does not exist");
        }
        boolean exist1=productRepo.existsById(id2);
        if(!exist1){
            throw new ApiRequestException("product with id"+id+"does not exist");
        }
        productRepo.deleteById(id2);
    }

    public Long findIdByEmail(Object principal) {
        return sellerRepo.findIdByEmail(principal.toString());
    }
    public List<CommentFromDto> showSellerComment(Long id){
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }

        return  sellerCommentRepo.findAll(id).stream().map(this::convertToDTO).collect(Collectors.toList());


    }



    private CommentFromDto convertToDTO(SellerComment sellerComment){
        CommentFromDto dto=new CommentFromDto();
        dto.setComment(sellerComment.getComment());
        dto.setFrom(sellerComment.getCustomer().getFirstName()+" "+sellerComment.getCustomer().getLastName());
        return dto;
    }

    public List showSellerFollower(Long id) {
            boolean exist = sellerRepo.existsById(id);
            if (!exist) {
                throw new ApiRequestException("seller with id" + id + "does not exist");
            }
        return followRepo.findCustomerBySellerId(id);
    }

    public void deleteSellerImageProductWithId(Long id, Long id2, ColorPropsDTO colorPropsDTO) {
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }
        boolean exist1=productRepo.existsById(id2);
        if(!exist1){
            throw new ApiRequestException("product with id"+id+"does not exist");
        }
        boolean exist2=colorPropsRepo.existsByColor(colorPropsDTO.getColor(),id2);
        if(!exist2){
            throw new ApiRequestException("color "+colorPropsDTO.getColor()+"does not exist for this product");
        }
        boolean exist3=imagesRepo.existsByImages(colorPropsDTO.getImages());
        if(!exist3){
            throw new ApiRequestException("image does not exist for this product");
        }
        ColorProps colorProps=colorPropsRepo.findByColor(colorPropsDTO.getColor(),id2);
        System.out.println(colorProps+"000000000000000000002");
        System.out.println(colorProps.getImages().size()+"000000000000000000000");
        System.out.println(colorProps.getImages());
        if(colorProps.getImages().size()==1){
            colorPropsRepo.deleteByColor(colorPropsDTO.getColor());
        }


        imagesRepo.deleteByImages(imagesRepo.findByImages(colorPropsDTO.getImages()));
        System.out.println(colorProps.getImages().size()+"000000000000000000001");
        System.out.println(colorProps.getImages());
    }

    public void addSellerImageProductWithId(Long id, Long id2, ColorPropsDTO colorPropsDTO) {
        boolean exist = sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }
        boolean exist1=productRepo.existsById(id2);
        if(!exist1){
            throw new ApiRequestException("product with id"+id+"does not exist");
        }
        boolean exist2=colorPropsRepo.existsByColor(colorPropsDTO.getColor(),id2);
        if(exist2){
            boolean exist3=imagesRepo.existsByImages(colorPropsDTO.getImages());
            if(exist3){
                throw new ApiRequestException("image does not exist for this product");
            }
            ColorProps colorProps=colorPropsRepo.findByColor(colorPropsDTO.getColor(),id2);
            Images images=new Images(colorPropsDTO.getImages());
            images.setColorProps(colorProps);
            imagesRepo.save(images);



        }
        else {
            ColorProps colorProps=new ColorProps(colorPropsDTO.getColor());
            colorProps.setProduct(productRepo.findProductById(id2));
            colorPropsRepo.save(colorProps);
            Images images=new Images(colorPropsDTO.getImages());
            images.setColorProps(colorProps);
            imagesRepo.save(images);
        }

    }

    public void updateSellerImage(Long id, ImageDto image) {
        boolean exist=sellerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("seller with id" + id + "does not exist");
        }
        Optional<Seller> user=sellerRepo.findUserById(id);
        userRepo.updateUserImage(image.getImage());

    }




}
