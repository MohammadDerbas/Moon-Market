package com.example.demo.services;

import com.example.demo.DTO.CustomerProductDTO;
import com.example.demo.DTO.OrderProductDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class OrderServices {
    private final OrderRepo orderRepo;
    private final SellerRepo sellerRepo;
    private final ProductRepo productRepo;
    private final PurchaseRepo purchaseRepo;
    private final CustomerRepo customerRepo;

    @Autowired
    public OrderServices(OrderRepo orderRepo, SellerRepo sellerRepo, ProductRepo productRepo, PurchaseRepo purchaseRepo, CustomerRepo customerRepo) {
        this.orderRepo = orderRepo;
        this.sellerRepo = sellerRepo;
        this.productRepo = productRepo;
        this.purchaseRepo = purchaseRepo;
        this.customerRepo = customerRepo;
    }
    public List<CustomerProductDTO> showOrders() {
            return orderRepo.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
        }
        public List <Product>showCustomerOrders(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String s  = authentication.getName();

        return orderRepo.findAlLProductByCustomer_Id(orderRepo.getIdByEmail(s));
        }
        public Double price(Long id){
        AtomicReference<Double> sum= new AtomicReference<>(0.0);
            orderRepo.findAlLOrdersByCustomer_Id(id).stream().forEach((order)-> sum.updateAndGet(v -> (v*10 + order.getPrice()*10)/10));
            return sum.get();
        }

    private CustomerProductDTO convertEntityToDto(Order order){
        CustomerProductDTO customerProductDTO=new CustomerProductDTO();
        customerProductDTO.setUserId(order.getCustomer().getId());
        customerProductDTO.setFirstName(order.getCustomer().getFirstName());
        customerProductDTO.setLastName(order.getCustomer().getLastName());
        customerProductDTO.setAddress(order.getCustomer().getAddress());
        customerProductDTO.setPostalCode(order.getCustomer().getPostalCode());
        customerProductDTO.setPhone(order.getCustomer().getPhone());
        customerProductDTO.setDescription(order.getProduct().getDescription());
        customerProductDTO.setBrand(order.getProduct().getBrand());
        customerProductDTO.setSize(order.getSize());
        customerProductDTO.setProductId(order.getProduct().getId());
        customerProductDTO.setPrice(order.getProduct().getPrice());
        customerProductDTO.setQuantityOrder(orderRepo.quantity(order.getId()));
        return customerProductDTO;
    }
    public void deleteAllByCustomerId(Long id){
        orderRepo.deleteAllByCustomerId(id);
    }

    public void updateQuantityProductByCustomerId(Long id){
        System.out.println(id+"8888888888888888888888");

        List<Product> product=orderRepo.findAlLProductByCustomer_Id(id);
        product.stream().forEach(product1 -> productRepo.updateQuantity(product1.getQuantity()-orderRepo.quantity(new OrderId(id,product1.getId())),product1.getId()));

    }
    public Long getIdByEmail(String email){
        return orderRepo.getIdByEmail(email);
    }
    public void updateQuantity(Integer quantity,Long id,Long id2){
        if(!(orderRepo.isOrderExist(new OrderId(id,id2)))){
            throw new ApiRequestException("this order not found ");
        }
        orderRepo.updateQuantity(quantity,new OrderId(id,id2));
    }
    public void buyOrders(Long id){
        Optional<Customer> customer=customerRepo.findUserByID(id);
        orderRepo.findAlLOrdersByCustomer_Id(id).stream().forEach(order -> {purchaseRepo.save(new Purchase(order.getCustomer().getId(),order.getProduct().getId(),order.getDate(),order.getPrice(),order.getQuantity()));
                customer.get().setCustomerPurchases(customer.get().getCustomerPurchases()+order.getPrice());}
        );
    }


    public void createOrder(List<OrderProductDTO> orders, String name) {

        boolean exist = customerRepo.existsByEmail(name);
        if(!exist){
            throw  new RuntimeException("user doesn t exists");
        }
        Customer customer = (Customer) customerRepo.findUserByEmail(name).get();
        Random rand=new Random();

        List<Order> newOrders=new ArrayList<>();
        System.out.println("ccccccccc" + customer.getId());


        orders.stream().forEach(order ->{
            System.out.println("zzzzzz" + order.getProductId());
            Order newOrder=new Order();

            newOrder.setCustomer(customer);
            Product product =productRepo.findProductById(order.getProductId());
            newOrder.setId(new OrderId(customer.getId(),product.getId()));


            newOrder.setColor(order.getColor());
            newOrder.setSize(order.getSize());
            newOrder.setPrice(order.getPrice());
            newOrder.setQuantity(order.getQuantity());
            newOrder.setProduct(product);
            System.out.println("your new order xxxxxx" +  newOrder);

            newOrders.add( newOrder);
            orderRepo.save(newOrder);

        } );
        customer.setOrders(newOrders);

        customerRepo.save(customer);




    }
}
