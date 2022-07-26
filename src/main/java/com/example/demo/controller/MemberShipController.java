package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import com.example.demo.services.MemberShipServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("member-ship")
public class MemberShipController {
    private final MemberShipServices memberShipServices;

    public MemberShipController(MemberShipServices memberShipServices) {
        this.memberShipServices = memberShipServices;
    }
    @GetMapping("/{membership}")
    @JsonView(View.View3.class)
    public List<Customer> findCustomersByMemberShip(@PathVariable String membership){
        return memberShipServices.findCustomersByMembership(membership);
    }
}
