package com.example.demo.controller;

import com.example.demo.DTO.OrderProductDTO;
import com.example.demo.entity.Order;
import com.example.demo.services.OrderServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import java.security.Principal;
import java.util.List;

@RestController

@RequestMapping("order")
public class OrderController {
private final OrderServices orderServices;
    @Autowired
    public OrderController(OrderServices orderServices) {

        this.orderServices = orderServices;
    }

    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    @PostMapping("/")
    public void createOrder(@RequestBody List<OrderProductDTO> order, Principal principal){

        orderServices.createOrder(order,principal.getName());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public List orders(){
         return orderServices.showOrders();
    }


}
