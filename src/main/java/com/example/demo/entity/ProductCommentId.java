package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductCommentId implements Serializable {
    @Column(
            name="customer_id"
    )
    private Long customerId;
    @Column(
            name="product_id"
    )
    private Long productId;

    public ProductCommentId() {
    }

    public ProductCommentId(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
        ProductCommentId that = (ProductCommentId) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId);
    }

    @Override
    public String toString() {
        return "ProductCommentId{" +
                "customerId=" + customerId +
                ", productId=" + productId +
                '}';
    }
}
