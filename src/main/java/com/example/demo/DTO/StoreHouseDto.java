package com.example.demo.DTO;

import com.example.demo.entity.Product;
import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Objects;

public class StoreHouseDto {
    @JsonView(View.View4.class)
    public Product product;
    @JsonView(View.View4.class)
    public Long sellerId;

    public StoreHouseDto() {
    }

    public StoreHouseDto(Product product, Long sellerId) {
        this.product = product;
        this.sellerId = sellerId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreHouseDto that = (StoreHouseDto) o;
        return Objects.equals(product, that.product) && Objects.equals(sellerId, that.sellerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, sellerId);
    }

    @Override
    public String toString() {
        return "StoreHouseDto{" +
                "product=" + product +
                ", sellerId=" + sellerId +
                '}';
    }
}
