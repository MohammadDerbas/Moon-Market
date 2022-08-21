package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity(
        name = "ColorProps"
)
@Table(
        name="color_props"
)
public class ColorProps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name = "color",nullable = false )
    @JsonView({View.View2.class,View.View4.class})
    private String color;
    @OneToMany(
            mappedBy = "colorProps"
    )
    @JsonView({View.View2.class,View.View4.class})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Img> images=new ArrayList<>();
    @ManyToOne
    @JoinColumn(
            name = "product_color_props_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "product_color_prop_id")

    )

    private Product product;


    public ColorProps() {
    }

    public ColorProps(String color) {
        this.color = color;
    }

    public ColorProps(String color, List<Img> images) {
        this.color = color;
        this.images = images;
    }
    public void addImages(Img image){
        if(!images.contains(image)){
            images.add(image);
            image.setColorProps(this);
        }
    }
    public void removeImages(Img image){
        if(images.contains(image)){
            images.remove(image);
            image.setColorProps(null);
        }
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Img> getImages() {
        return images;
    }

    public void setImages(List<Img> images) {
        this.images = images;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ColorProps{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", images=" + images +
                '}';
    }
}
