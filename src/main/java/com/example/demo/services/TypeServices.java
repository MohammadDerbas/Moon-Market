package com.example.demo.services;

import com.example.demo.entity.Product;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.TypeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServices {
    private final TypeRepo typeRepo;

    public TypeServices(TypeRepo typeRepo) {
        this.typeRepo = typeRepo;
    }
    public List<Product> findProductsByType(String type){
        boolean exists=typeRepo.existsByType(type);
        if(!exists){
            throw new ApiRequestException("Type"+type+" does not exists");
        }
        return typeRepo.findProductsByType(type);
    }
}
