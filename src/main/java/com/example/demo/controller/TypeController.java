package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.services.TypeServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("type")
public class TypeController {
private final TypeServices typeServices;

    public TypeController(TypeServices typeServices) {
        this.typeServices = typeServices;
    }
    @GetMapping("/{type}")
    @JsonView(View.View2.class)
    public List<Product> findProductsByType(@PathVariable String type){
        return typeServices.findProductsByType(type);
    }
}
