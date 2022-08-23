package com.example.demo.entity;

import javax.persistence.*;

@Entity(name="PrizeChanger")
@Table(name="prize_changer")
public class PrizeChanger {
    @EmbeddedId
    private PrizeChangerId id;
    @ManyToOne
    @MapsId("prizeId")
    @JoinColumn(
            name="prize_id",
            foreignKey = @ForeignKey(name="prize_prizeChanger_id_fk")
    )
    private Prize prize;
    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(
            name = "customer_id",
            foreignKey = @ForeignKey(name="customer_prizeChanger_id_fk")
    )
    private Customer customer;

    public PrizeChanger() {
    }

    public PrizeChanger(PrizeChangerId id, Prize prize, Customer customer) {
        this.id = id;
        this.prize = prize;
        this.customer = customer;
    }

    public PrizeChanger(Prize prize, Customer customer) {
        this.prize = prize;
        this.customer = customer;
    }

    public PrizeChangerId getId() {
        return id;
    }

    public void setId(PrizeChangerId id) {
        this.id = id;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
