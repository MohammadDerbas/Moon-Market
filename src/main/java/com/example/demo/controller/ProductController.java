package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.services.CustomerServices;
import com.example.demo.services.ProductServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductServices productServices;
    private final CustomerServices customerServices;
    @Autowired
    public ProductController(ProductServices productServices, CustomerServices customerServices) {
        this.productServices = productServices;
        this.customerServices = customerServices;
    }
    @GetMapping("/")
    @JsonView(View.View2.class)
    List<Product> productList(){
        return productServices.listProduct();

    }

    @GetMapping(path="/{id}/product-comments")
    public List<Product> showProductComments(@PathVariable Long id) {
        return customerServices.showProductComment(id);
    }
}
