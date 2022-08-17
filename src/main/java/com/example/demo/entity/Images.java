package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
@Entity(name = "Images")
@Table(
        name="images"
)
public class Images {
    @Id
    @Column(name = "id",updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "images",nullable = false)
    @JsonView({View.View2.class,View.View4.class})
    private String images;
    @ManyToOne
    @JoinColumn(
            name = "image_color_props_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "image_color_props_id")

    )
    private ColorProps colorProps;

    public Images(String images) {
        this.images = images;
    }

    public Images() {
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public ColorProps getColorProps() {
        return colorProps;
    }

    public void setColorProps(ColorProps colorProps) {
        this.colorProps = colorProps;
    }

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", images='" + images + '\'' +
                '}';
    }
}
