package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StoreHouseId implements Serializable {
    @Column(
            name="seller_id"
    )
    private Long sellerId;
    @Column(
            name="product_id"
    )
    private Long productId;

    public StoreHouseId() {
    }

    public StoreHouseId(Long sellerId, Long productId) {
        this.sellerId = sellerId;
        this.productId = productId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreHouseId that = (StoreHouseId) o;
        return Objects.equals(sellerId, that.sellerId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerId, productId);
    }

    @Override
    public String toString() {
        return "StoreHouseId{" +
                "sellerId=" + sellerId +
                ", productId=" + productId +
                '}';
    }
}
