package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LikeId implements Serializable {
    private static final long serialVersionUID = 6716733645575345027L;
    @Column(
            name="customer_id"
    )
    private Long customerId;
    @Column(
            name="product_id"
    )
    private Long productId;

    public LikeId() {
    }

    public LikeId(Long customerId, Long productId) {
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
        LikeId likeId = (LikeId) o;
        return Objects.equals(customerId, likeId.customerId) && Objects.equals(productId, likeId.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId);
    }
}
