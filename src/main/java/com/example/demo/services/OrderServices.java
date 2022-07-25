package com.example.demo.services;

import com.example.demo.DTO.CustomerProductDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.repo.OrderRepo;
import com.example.demo.repo.SellerRepo;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServices {
    private final OrderRepo orderRepo;
    private final SellerRepo sellerRepo;

    @Autowired
    public OrderServices(OrderRepo orderRepo, SellerRepo sellerRepo) {
        this.orderRepo = orderRepo;
        this.sellerRepo = sellerRepo;
    }
    public List<CustomerProductDTO> showOrders() {
            return orderRepo.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
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
        customerProductDTO.setSize(order.getProduct().getSize());
        customerProductDTO.setProductId(order.getProduct().getId());
        return customerProductDTO;
    }
}
