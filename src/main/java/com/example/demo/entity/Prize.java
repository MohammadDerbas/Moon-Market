package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="Prize")
@Table(name="prize")
public class Prize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Prize;
    private Integer points;
    @OneToMany(
            mappedBy = "prize"
    )
    private List<PrizeChanger> prizeChangers=new ArrayList<>();

    public Prize() {
    }

    public Prize(Long id, String prize, Integer points) {
        this.id = id;
        Prize = prize;
        this.points = points;
    }

    public Prize(String prize, Integer points) {
        Prize = prize;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrize() {
        return Prize;
    }

    public void setPrize(String prize) {
        Prize = prize;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "id=" + id +
                ", Prize='" + Prize + '\'' +
                ", points=" + points +
                '}';
    }
}
