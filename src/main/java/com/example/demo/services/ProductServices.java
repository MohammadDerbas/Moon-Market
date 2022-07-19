package com.example.demo.services;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ProductServices {

    private final ProductRepo productRepo;
    @Autowired
    public ProductServices(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public List<Product> listProduct() {
        return productRepo.findAll();
    }
}
