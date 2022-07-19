package com.example.demo.services;

import com.example.demo.entity.*;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.OrderRepo;
import com.example.demo.repo.ProductRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServices {
    private final CustomerRepo customerRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    @Autowired
    public CustomerServices(CustomerRepo customerRepo, @Qualifier("customerRepo") UserRepo userRepo, ProductRepo productRepo, OrderRepo orderRepo) {
        this.customerRepo = customerRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Optional<Customer> getInfoCustomer(Long id) {
        boolean exists = customerRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("customer with id" + id + "does not exist");
        }
        Optional<Customer> customer = customerRepo.findUserByID(id);
        return customer;
    }

    public void updateCustomerInfo(Long id, String userName, String firstName, String lastName, String email, String password, String address, String phone, String postalCode) {
        boolean exists = userRepo.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Customer with id " + id + "does not exist");
        }
        User customer = userRepo.getReferenceById(id);
        if (userName != null && userName.length() > 0 && !Objects.equals(customer.getUserName(), userName)) {
            Optional<User> optionalCustomer = userRepo.findUserByUserName(userName);
            if (optionalCustomer.isPresent()) {
                throw new IllegalStateException("userName taken");

            }
            customer.setUserName(userName);

        }
        if (email != null && email.length() > 0 && !Objects.equals(customer.getEmail(), email)) {
            Optional<User> studentOptional = userRepo.findUserByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
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
            throw new IllegalStateException("customer with id" + id + "does not exist");
        }
        boolean exist1 = productRepo.existsById(id2);
        if (!exist1) {
            throw new IllegalStateException("product with id" + id2 + "does not exist");
        }
        Optional<Customer> customer=customerRepo.findUserByID(id);
        Product product=productRepo.findProductById(id2);

        Order order=new Order(new OrderId(id,id2),customer.get(),product);
        orderRepo.save(order);

    }

    public List showOrders(Long id) {
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new IllegalStateException("customer with id" + id + "does not exist");
        }
        return orderRepo.findAlLProductByCustomer_Id(id);
    }

    public void deleteOrder(Long id, Long id2) {
        boolean exist = customerRepo.existsById(id);
        if (!exist) {
            throw new IllegalStateException("customer with id" + id + "does not exist");
        }
        boolean exist1 = productRepo.existsById(id2);
        if (!exist1) {
            throw new IllegalStateException("product with id" + id2 + "does not exist");
        }
        OrderId orderId=new OrderId(id,id2);
        orderRepo.deleteById(orderId);
    }
}
