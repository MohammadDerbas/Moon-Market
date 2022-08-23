package com.example.demo.services;

import com.example.demo.entity.Product;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.CustomerRepo;
import com.example.demo.repo.ProductRepo;
import com.example.demo.repo.PurchaseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServices {
    private final CustomerRepo customerRepo;
    private final PurchaseRepo purchaseRepo;
    private final ProductRepo productRepo;

    public RecommendationServices(CustomerRepo customerRepo, PurchaseRepo purchaseRepo, ProductRepo productRepo) {
        this.customerRepo = customerRepo;
        this.purchaseRepo = purchaseRepo;
        this.productRepo = productRepo;
    }

    public List<Product> familiarProduct(Long id) {
        boolean exist=productRepo.existsById(id);
        if(!exist){
            throw new ApiRequestException("this product is not found");
        }
        Product product=productRepo.findProductById(id);
        return purchaseRepo.familiarProduct(id);
    }
}
