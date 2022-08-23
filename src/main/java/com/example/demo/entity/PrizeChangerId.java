package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrizeChangerId implements Serializable {
    @Column(
            name="prize_id"
    )
    private Long prizeId;
    @Column(
            name="customer_id"
    )
    private Long customerId;

    public PrizeChangerId() {
    }

    public PrizeChangerId(Long prizeId, Long customerId) {
        this.prizeId = prizeId;
        this.customerId = customerId;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrizeChangerId prizeId1 = (PrizeChangerId) o;
        return Objects.equals(prizeId, prizeId1.prizeId) && Objects.equals(customerId, prizeId1.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prizeId, customerId);
    }
}
