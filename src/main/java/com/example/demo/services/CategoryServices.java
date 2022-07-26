package com.example.demo.services;

import com.example.demo.entity.Product;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServices {
    private final CategoryRepo categoryRepo;

    public CategoryServices(CategoryRepo categoryRepo) {

        this.categoryRepo = categoryRepo;
    }

    public List<Product>findProductByCategory(String category){
        boolean exists=categoryRepo.existsByCategory(category);
        if(!exists){
            throw new ApiRequestException("Category with type"+category+" does not exists");
        }
        return categoryRepo.findProductsByCategory(category);
    }
}
