package com.example.demo.DTO;

import java.util.Objects;

public class OrderProductDTO {

    public Long productId;
    public String color;
    public String size;
    public Integer quantity;
    public Double price;


    public OrderProductDTO(Long productId, String color, String size, Integer quantity, Double price) {
        this.productId = productId;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}
