package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.services.RecommendationServices;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("recommendation")
public class RecommendationController {
    private final RecommendationServices recommendationServices;

    public RecommendationController(RecommendationServices recommendationServices) {
        this.recommendationServices = recommendationServices;
    }

    @GetMapping("/")
    @JsonView(View.View2.class)
    public List<Product> familiarProduct(@RequestParam Long id){
       return recommendationServices.familiarProduct(id);
    }
}
