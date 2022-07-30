package com.example.demo.DTO;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Size;

import java.time.LocalDate;
import java.util.Date;

public class CustomerProductPurchaseDTO {
    private String name;
    private String type;
    private String description;
    private Brand brand;
    private Size size;
    private LocalDate date;
    private Integer quantity;
    private Double price;

    public CustomerProductPurchaseDTO() {
    }

    public CustomerProductPurchaseDTO(String name, String type, String description, Brand brand, Size size, LocalDate date, Integer quantity, Double price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.brand = brand;
        this.size = size;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
