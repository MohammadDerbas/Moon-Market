package com.example.demo.services;

import com.example.demo.entity.Img;
import com.example.demo.repo.ImgRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImgServices {
    private final ImgRepo imgRepo;

    public ImgServices(ImgRepo imgRepo) {
        this.imgRepo = imgRepo;
    }

    public void save(Img img) {
        imgRepo.save(img);
    }
}
