package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FollowersId implements Serializable {
    @Column(
            name="customer_id"
    )
    private Long customerId;
    @Column(
            name="seller_id"
    )
    private Long sellerId;

    public FollowersId() {
    }

    public FollowersId(Long customerId, Long sellerId) {
        this.customerId = customerId;
        this.sellerId = sellerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
        FollowersId that = (FollowersId) o;
        return Objects.equals(customerId, that.customerId) && Objects.equals(sellerId, that.sellerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, sellerId);
    }
}

