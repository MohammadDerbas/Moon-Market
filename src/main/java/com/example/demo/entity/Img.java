package com.example.demo.entity;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity(name = "Img")
@Table(name = "img")
public class Img {
    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private MultipartFile images;

    public Img() {
    }

    public MultipartFile getImages() {
        return images;
    }

    public void setImages(MultipartFile images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Img{" +
                "id=" + id +
                ", images=" + images +
                '}';
    }
}
