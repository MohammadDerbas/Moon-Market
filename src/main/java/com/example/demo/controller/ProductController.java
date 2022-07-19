package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.services.ProductServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductServices productServices;
    @Autowired
    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }
    @GetMapping("/")
    @JsonView(View.View2.class)
    List<Product> productList(){
        return productServices.listProduct();

    }
}
