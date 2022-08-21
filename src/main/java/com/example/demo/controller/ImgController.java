package com.example.demo.controller;

import com.example.demo.entity.Img;
import com.example.demo.entity.ImgProfilePic;
import com.example.demo.repo.ImageProfilePicRepo;
import com.example.demo.repo.ImgRepo;
import com.example.demo.services.ImgServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.Util.ImageUtility;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("img")
public class ImgController {
    private final ImgServices imgServices;
    private final ImgRepo imgRepo;
    private final ImageProfilePicRepo imageProfilePicRepo;

    public ImgController(ImgServices imgServices, ImgRepo imgRepo, ImageProfilePicRepo imageProfilePicRepo) {
        this.imgServices = imgServices;
        this.imgRepo = imgRepo;
        this.imageProfilePicRepo = imageProfilePicRepo;
    }
   /* @PostMapping
    public ResponseEntity<?> saveImg(@RequestParam("imageFile")MultipartFile imageFile) {
       String fileName=imageFile.getOriginalFilename();
       try{
           imageFile.transferTo(new File("c:\\upload\\"+fileName));
           imgServices.save(new Img("c:\\upload\\"+fileName));
       } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }

       return ResponseEntity.ok("File upload successfully");
    }*/
   // "/upload/image"
    @PostMapping()
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") List<MultipartFile> file1)
            throws IOException {
        String fileName="";

        for (MultipartFile file:file1
             ) {

            Img img1 = new Img(file.getOriginalFilename(), file.getContentType(), ImageUtility.compressImage(file.getBytes()),"http://localhost:8080/img/get/image/"+file.getOriginalFilename());
            imgRepo.save(img1);
           /* imgRepo.save(Img.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .image(ImageUtility.compressImage(file.getBytes())).build());*/
            fileName+=file.getOriginalFilename();
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        fileName));
    }
    @GetMapping(path = {"/get/image/info/{name}"})
    public Img getImageDetails(@PathVariable("name") String name) throws IOException {

        final Optional<Img> dbImage = imgRepo.findByName(name);
        Img img1 = new Img(dbImage.get().getName(), dbImage.get().getType(), ImageUtility.decompressImage(dbImage.get().getImage()),"http://localhost:8080/img/get/image/"+dbImage.get().getName());
    return img1;
        /*return Img.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();*/
    }
    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        final Optional<Img> dbImage = imgRepo.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
    @GetMapping(path = {"/get/profile_pic/{name}"})
    public ResponseEntity<byte[]> getProfileImage(@PathVariable("name") String name) throws IOException {

        final Optional<ImgProfilePic> dbImage = imageProfilePicRepo.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }
}
