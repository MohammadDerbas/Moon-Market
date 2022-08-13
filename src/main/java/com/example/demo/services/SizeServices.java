package com.example.demo.services;

import com.example.demo.entity.Product;
import com.example.demo.entity.Size;
import com.example.demo.exception.ApiRequestException;
import com.example.demo.repo.SizeRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServices {
    private final SizeRepo sizeRepo;

    public SizeServices(SizeRepo sizeRepo) {
        this.sizeRepo = sizeRepo;
    }
    public List<Product> findProductsBySize(List <String> size){
        boolean exists=sizeRepo.existsBySizeIn(size);
        if(!exists){
            throw new ApiRequestException("Size "+size+" does not exists");
        }
        return sizeRepo.findProductsBySizeIn(size);
    }
}
