package com.example.demo.DTO;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Size;

import java.util.List;

public class CustomerProductDTO {
private Long userId;
private String firstName;
private String lastName;
private String address;
private String postalCode;
private String phone;
private Long productId;
private String description;
private Brand brand;
private List<Size> size;
private Double price;
private Integer quantityOrder;
    public CustomerProductDTO() {
    }

    public CustomerProductDTO(Long userId, String firstName, String lastName, String address, String postalCode, String phone, Long productId, String description, Brand brand, List<Size> size, Double price, Integer quantityOrder) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.productId = productId;
        this.description = description;
        this.brand = brand;
        this.size = size;
        this.price = price;
        this.quantityOrder = quantityOrder;
    }



    public Integer getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Integer quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public List<Size> getSize() {
        return size;
    }

    public void setSize(List<Size> size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

