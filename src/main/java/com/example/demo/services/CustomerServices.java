package com.example.demo.services;

import com.example.demo.DTO.CommentDTO;
import com.example.demo.DTO.CommentFromDto;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServices {
    private final CustomerRepo customerRepo;
    private final SellerRepo sellerRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final FollowRepo followRepo;
    private final SellerCommentRepo sellerCommentRepo;
    private final ProductCommentRepo productCommentRepo;

    @Autowired
    public CustomerServices(CustomerRepo customerRepo, SellerRepo sellerRepo, @Qualifier("customerRepo") UserRepo userRepo, ProductRepo productRepo, OrderRepo orderRepo, FollowRepo followRepo, SellerCommentRepo sellerCommentRepo, ProductCommentRepo productCommentRepo) {
        this.customerRepo = customerRepo;
        this.sellerRepo = sellerRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.followRepo = followRepo;
        this.sellerCommentRepo = sellerCommentRepo;
        this.productCommentRepo = productCommentRepo;
    }

    public Optional<Customer> getInfoCustomer(Long id) {
        boolean exists = customerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        Optional<Customer> customer = customerRepo.findUserByID(id);
        return customer;
    }

    public void updateCustomerInfo(Long id,  String firstName, String lastName, String email, String password, String address, String phone, String postalCode) {
        boolean exists = userRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("Customer with id " + id + "does not exist");
        }
        User customer = userRepo.getReferenceById(id);



        if (email != null && email.length() > 0 && !Objects.equals(customer.getEmail(), email)) {
            Optional<User> studentOptional = userRepo.findUserByEmail(email);
            if (studentOptional.isPresent()) {
                throw new ApiRequestException("email taken");
            }
            customer.setEmail(email);
        }
        if (firstName != null && firstName.length() > 0 && !Objects.equals(customer.getFirstName(), firstName)) {

            customer.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(customer.getLastName(), lastName)) {

            customer.setLastName(lastName);
        }
        if (address != null && address.length() > 0 && !Objects.equals(customer.getAddress(), address)) {

            customer.setAddress(address);
        }
        if (phone != null && phone.length() > 0 && !Objects.equals(customer.getPhone(), phone)) {

            customer.setPhone(phone);
        }
        if (postalCode != null && postalCode.length() > 0 && !Objects.equals(customer.getPostalCode(), postalCode)) {

            customer.setPostalCode(postalCode);
        }
        if (password != null && password.length() > 0 && !Objects.equals(customer.getPassword(), password)) {

            customer.setPassword(password);
        }

        userRepo.save(customer);
    }


    public void orderProduct(Long id, Long id2) {
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        boolean exist1 = productRepo.existsById(id2);
        if (!exist1) {
            throw new ApiRequestException("product with id" + id2 + "does not exist");
        }
        Optional<Customer> customer=customerRepo.findUserByID(id);
        Product product=productRepo.findProductById(id2);

        Order order=new Order(new OrderId(id,id2),customer.get(),product);
        orderRepo.save(order);

    }
    public void followSeller(Long id, Long id2) {
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        boolean exist1 = sellerRepo.existsById(id2);
        if (!exist1) {
            throw new ApiRequestException("seller with id" + id2 + "does not exist");
        }
        Boolean exist2=followRepo.isCustmoerFollowSeller(id,id2);
        if(exist2){
            throw new ApiRequestException("customer with id" + id + " is following seller with id "+id2);

        }
        Optional<Customer> customer=customerRepo.findUserByID(id);
        Optional<Seller> seller=sellerRepo.findUserById(id2);


        Followers follow=new Followers(new FollowersId(id,id2),customer.get(),seller.get());
        followRepo.save(follow);

    }

    public List showOrders(Long id) {
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        return orderRepo.findAlLProductByCustomer_Id(id);
    }

    public void deleteOrder(Long id, Long id2) {
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        boolean exist1 = productRepo.existsById(id2);
        if (!exist1) {
            throw new ApiRequestException("product with id" + id2 + "does not exist");
        }
        OrderId orderId=new OrderId(id,id2);
        orderRepo.deleteById(orderId);
    }

    public void removeFollow(Long id, Long id2) {
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        boolean exist1 = sellerRepo.existsById(id2);
        if (!exist1) {
            throw new ApiRequestException("seller with id" + id2 + "does not exist");
        }
        Boolean exist2=followRepo.isCustmoerFollowSeller(id,id2);
        if(!exist2){
            throw new ApiRequestException("customer with id" + id + " does not follow seller with id "+id2);

        }
        FollowersId followersId =new FollowersId(id,id2);
        followRepo.deleteById(followersId);
    }

    public void commentSeller(Long id, Long id2, CommentDTO comment) {
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        boolean exist1 = sellerRepo.existsById(id2);
        if (!exist1) {
            throw new ApiRequestException("seller with id" + id2 + "does not exist");
        }
        Optional<Customer> customer=customerRepo.findUserByID(id);
        Optional<Seller> seller=sellerRepo.findUserById(id2);
        String sellerComments=comment.getComment();
        SellerComment sellerComment=new SellerComment(sellerComments);
        sellerComment.setSeller(seller.get());
        sellerComment.setCustomer(customer.get());
        sellerCommentRepo.save(sellerComment);
    }
    public void commentProduct(Long id, Long id2, CommentDTO comment) {
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        boolean exist1 = productRepo.existsById(id2);
        if (!exist1) {
            throw new ApiRequestException("product with id" + id2 + "does not exist");
        }
        Optional<Customer> customer=customerRepo.findUserByID(id);
        Product product=productRepo.findProductById(id2);
        String productComments=comment.getComment();
        ProductComment productComment=new ProductComment(productComments);
        productComment.setCustomer(customer.get());
        productComment.setProduct(product);
        productCommentRepo.save(productComment);
    }

    public List showProductComment(Long id){
        boolean exist = productRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("product with id" + id + "does not exist");
        }
        return  productCommentRepo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());


    }




    private CommentFromDto convertToDTO(ProductComment productComment){
        CommentFromDto dto=new CommentFromDto();
        dto.setComment(productComment.getComment());
        dto.setFrom(productComment.getCustomer().getFirstName()+" "+productComment.getCustomer().getLastName());
        return dto;
    }


    public List showCustomerFollowing(Long id) {
            boolean exist = customerRepo.existsById(id);
            if (!exist) {
                throw new ApiRequestException("customer with id" + id + "does not exist");
            }
        return followRepo.findSellerByCustomerId(id);
    }
}
