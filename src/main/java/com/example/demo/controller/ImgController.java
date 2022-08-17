package com.example.demo.controller;

import com.example.demo.entity.Img;
import com.example.demo.services.ImgServices;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("img")
public class ImgController {
    private final ImgServices imgServices;

    public ImgController(ImgServices imgServices) {
        this.imgServices = imgServices;
    }

    @RequestMapping(path = "/employee", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String saveEmployee(@ModelAttribute Img img) {
        imgServices.save(img);
        return "employee/success";
    }
}
