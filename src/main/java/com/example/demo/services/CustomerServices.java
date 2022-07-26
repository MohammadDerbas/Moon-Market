package com.example.demo.services;

import com.example.demo.DTO.*;
import com.example.demo.Util.ImageUtility;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
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
    private final LikeRepo likeRepo;

    @Autowired
    public CustomerServices(CustomerRepo customerRepo, SellerRepo sellerRepo, @Qualifier("customerRepo") UserRepo userRepo, ProductRepo productRepo, OrderRepo orderRepo, FollowRepo followRepo, SellerCommentRepo sellerCommentRepo, ProductCommentRepo productCommentRepo, LikeRepo likeRepo) {
        this.customerRepo = customerRepo;
        this.sellerRepo = sellerRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.followRepo = followRepo;
        this.sellerCommentRepo = sellerCommentRepo;
        this.productCommentRepo = productCommentRepo;
        this.likeRepo = likeRepo;
    }

    public Optional<Customer> getInfoCustomer(Long id) {
        boolean exists = customerRepo.existsById(id);
        if (!exists) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        Optional<Customer> customer = customerRepo.findUserByID(id);
        return customer;
    }

    public void updateCustomerInfo(String name,  String firstName, String lastName, String email, String password, String address, String phone, String postalCode) {

       Long id=customerRepo.findUserByEmail(name).get().getId();

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


    public void orderProduct(Long id, Long id2,Integer quantity) {
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        boolean exist1 = productRepo.existsById(id2);
        if (!exist1) {
            throw new ApiRequestException("product with id" + id2 + "does not exist");
        }

        Product product=productRepo.findProductById(id2);
        Optional<Customer> customer=customerRepo.findUserByID(id);
        if(orderRepo.isOrderExist(new OrderId(id,id2))){
            Integer oldQuantity=orderRepo.quantity(new OrderId(id,id2));
            if(orderRepo.quantity(new OrderId(id,id2))+quantity>productRepo.quantityByProduct(product)){
                throw new ApiRequestException("there is no enough quantity of this product");

            }
            Order order=new Order(new OrderId(id,id2),customer.get(),product,quantity+oldQuantity);
            orderRepo.save(order);
            return;

        }

        if(quantity>productRepo.quantityByProduct(product)){
            throw new ApiRequestException("there is no enough quantity of this product");
        }

        Order order=new Order(new OrderId(id,id2),customer.get(),product,quantity);
        orderRepo.save(order);


    }


    public List showOrders(String name) {
        Long id = customerRepo.findUserByEmail(name).get().getId();
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        return orderRepo.findAlLOrdersByCustomer_Id(id).stream().map(this::convertEntityToDto).collect(Collectors.toList());

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
        double sellerRating=comment.getRating();
        SellerComment sellerComment=new SellerComment(sellerComments,sellerRating);
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

        return  productCommentRepo.findAllProductCommentByProductId(id).stream().map(this::convertToDTO).collect(Collectors.toList());


    }
    private CustomerProductDTO convertEntityToDto(Order order){
        AtomicReference<String> imgurl= new AtomicReference<>("https://static.zara.net/photos///2022/I/0/1/p/2569/263/400/2/w/385/2569263400_6_1_1.jpg?ts=1660213342477");
        order.getProduct().getColorProps().forEach(colorProps -> {
           if(colorProps.getColor()==order.getColor()){
           imgurl.set(colorProps.getImages().get(0).getUrl());
           }
        });
        CustomerProductDTO customerProductDTO=new CustomerProductDTO();
        customerProductDTO.setReference(order.getReference());
        customerProductDTO.setImgUrl(String.valueOf(imgurl));
        customerProductDTO.setUserId(order.getCustomer().getId());
        customerProductDTO.setFirstName(order.getCustomer().getFirstName());
        customerProductDTO.setLastName(order.getCustomer().getLastName());
        customerProductDTO.setAddress(order.getCustomer().getAddress());
        customerProductDTO.setPostalCode(order.getCustomer().getPostalCode());
        customerProductDTO.setPhone(order.getCustomer().getPhone());
        customerProductDTO.setDescription(order.getProduct().getDescription());
        customerProductDTO.setBrand(order.getProduct().getBrand());
        customerProductDTO.setDate((order.getDate()));
        customerProductDTO.setSize(order.getSize());
        customerProductDTO.setColor(order.getColor());
        customerProductDTO.setStatus(order.getStatus());
        customerProductDTO.setProductId(order.getProduct().getId());
        customerProductDTO.setPrice(order.getPrice());
        customerProductDTO.setQuantityOrder(orderRepo.quantity(order.getId()));
        return customerProductDTO;
    }




    private CommentFromDto convertToDTO(ProductComment productComment){
        CommentFromDto dto=new CommentFromDto();
        dto.setComment(productComment.getComment());
        dto.setId(productComment.getId());
        dto.setTime(productComment.getTime());
        dto.setFrom(productComment.getCustomer().getFirstName()+" "+productComment.getCustomer().getLastName());
        return dto;
    }


    public List showCustomerFollowing(String name) {


            boolean exist = customerRepo.existsByEmail(name);
            if (!exist) {
                throw new ApiRequestException("customer with id does not exist");
            }
            Customer customer= (Customer) customerRepo.findUserByEmail(name).get();
        return followRepo.findSellerByCustomerId(customer.getId());
    }


    public void updateCustomerImage(Long id, MultipartFile multipartFile) throws IOException {
        boolean exist=customerRepo.existsById(id);
        if (!exist) {
            throw new ApiRequestException("customer with id" + id + "does not exist");
        }
        Optional<Customer> user=customerRepo.findUserByID(id);
        userRepo.updateUserImage(new ImgProfilePic(multipartFile.getOriginalFilename(),multipartFile.getContentType(), ImageUtility.compressImage(multipartFile.getBytes()),"http://localhost:8080/img/profile_pic/image/"+multipartFile.getOriginalFilename())
        );
    }

    public List showCustomerLikedProduct(Long id) {
        return likeRepo.findProductByCustomerId(id);
    }

    public Customer getMyInfo(String name) {


        return (Customer) customerRepo.findUserByEmail(name).get();
    }
}
