package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.services.CategoryServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final CategoryServices categoryServices;


    public CategoryController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }
    @GetMapping("/{category}")
    @JsonView(View.View2.class)
    public List<Product> findProductsByCategory( @PathVariable String category){
        return categoryServices.findProductByCategory(category);
    }
}
