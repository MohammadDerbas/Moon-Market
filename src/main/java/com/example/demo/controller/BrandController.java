package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.services.BrandServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {
    private final BrandServices brandServices;

    public BrandController(BrandServices brandServices) {
        this.brandServices = brandServices;
    }
    @JsonView(View.View2.class)
    @GetMapping("/{name}")
    public List<Product> findProductByBrand(@PathVariable String name){
        return brandServices.findProductByBrand(name);
    }
}
