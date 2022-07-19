package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseId implements Serializable {
    @Column(
            name = "customer_id"
    )
    private long customerId;
    @Column(
            name="product_id"
    )
    private long productId;

    public PurchaseId() {
    }


    public PurchaseId(long customerId, long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseId buyId = (PurchaseId) o;
        return Objects.equals(customerId, buyId.customerId) && Objects.equals(productId, buyId.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, productId);
    }
}
