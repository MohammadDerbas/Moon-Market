package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.services.SizeServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("size")
public class SizeController {
    private final SizeServices sizeServices;

    public SizeController(SizeServices sizeServices) {
        this.sizeServices = sizeServices;
    }
    @GetMapping("/{size}")
    @JsonView(View.View2.class)
    public List<Product> findProductsBySize(@PathVariable String size){
        return sizeServices.findProductsBySize(size);
    }
}
