package com.example.demo.services;

import com.example.demo.entity.Product;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.BrandRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServices {
    private final BrandRepo brandRepo;

    public BrandServices(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    public List<Product> findProductByBrand(String name) {
        boolean exists=brandRepo.existsByBrand(name);
        if(!exists){
            throw new ApiRequestException("Brand with type"+name+" does not exists");
        }
       return brandRepo.findProductByBrand(name);
    }
}
