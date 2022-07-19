package com.example.demo.entity;

import javax.persistence.*;

@Entity(name="PointPrize")
@Table(name="point_prize")
public class PointPrize{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(
            name = "points",
            nullable = false
    )
    private Integer points;
    @Column(
            name="prize",
            nullable = false
    )
    private String prize;

    public PointPrize() {
    }

    public PointPrize(Integer points, String prize) {
        this.points = points;
        this.prize = prize;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "PointPrize{" +
                "id=" + id +
                ", points=" + points +
                ", prize='" + prize + '\'' +
                '}';
    }






}

