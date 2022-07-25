package com.example.demo.services;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.DTO.CommentFromDto;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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

    @Autowired
    public SellerServices(SellerRepo sellerRepo, @Qualifier("sellerRepo") UserRepo userRepo, StoreHouseRepo storeHouseRepo, ProductRepo productRepo, BrandRepo brandRepo, TypeRepo typeRepo, CategoryRepo categoryRepo, SizeRepo sizeRepo, SellerCommentRepo sellerCommentRepo, FollowRepo followRepo) {
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
    }

    public Product getInfoSellerProductWithId(Long id, Long id2) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        boolean productExists=productRepo.existsById(id2);
        if (!productExists) {
            throw new ApiRequestException("Product with id " + id2 + "does not exist");
        }

        return productRepo.getReferenceById(id2);
    }

    public Optional<Seller> getSeller(Long id) {
        boolean exists = sellerRepo.existsById(id);
        System.out.println(exists);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }
        Optional<Seller> seller = sellerRepo.findUserById(id);
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
        boolean exists2=sizeRepo.existsBySize(product.getSize().getSize());
        sizeRepo.findBySize(product.getSize().getSize()).addProduct(product);
        if(!exists2)
            throw new ApiRequestException("this size does not exist");
        boolean exists3=categoryRepo.existsByCategory(product.getCategory().getCategory());
        categoryRepo.findByCategory(product.getCategory().getCategory()).addProduct(product);
        if(!exists3)
            throw new ApiRequestException("this category does not exist");


        productRepo.save(product);
        //User seller=  sellerRepo.getReferenceById(id);
        Optional<Seller> seller = sellerRepo.findUserById(id);
        StoreHouse op = new StoreHouse(new StoreHouseId(id, product.getId()), seller.get(), product);
        storeHouseRepo.save(op);
    }

    public List<Product> showProduct(Long id) {
        boolean exists = sellerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Seller with id " + id + "does not exist");
        }

        return productRepo.showProductWithSpecificSeller(id);
    }

    public List<User> showSellers() {
        List<User> sellers = sellerRepo.findAll();
        return sellers;
    }

    public void updateSellerProductWithId(Long id, Long id2, String description, Integer quantity, Integer price, String size, String type, String brand, String category) {
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
        if (size != null && size.length() > 0 ) {
            boolean exist=sizeRepo.existsBySize(size);
           if(!exist){
               throw new ApiRequestException("size"+size+"does not exist");

           }

            product.setSize(sizeRepo.findBySize(size));
        }
        if (type != null && type.length() > 0 ) {
            boolean exist=sizeRepo.existsBySize(type);
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
}
