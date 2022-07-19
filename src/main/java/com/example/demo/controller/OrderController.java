package com.example.demo.controller;

import com.example.demo.services.OrderServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
private final OrderServices orderServices;
    @Autowired
    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }
    @GetMapping("/")
    public List orders(){
         return orderServices.showOrders();
    }

}
