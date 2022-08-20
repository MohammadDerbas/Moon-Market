package com.example.demo.entity;

import com.example.demo.view.View;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "img")
/*@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor*/
public class Img {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonView({View.View2.class,View.View4.class})

    @Column(name = "name")
    private String name;
    @JsonView({View.View2.class,View.View4.class})

    @Column(name = "type")
    private String type;
    @JsonView({View.View2.class,View.View4.class})

    @Column(name = "image", unique = false, nullable = false, length = 100000)
    private byte[] image;
    @ManyToOne
    @JoinColumn(
            name = "image_color_props_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "image_color_props_id")

    )
    private ColorProps colorProps;

    public Img() {
    }

    public Img(String name, String type, byte[] image) {
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public Img(String name, String type, byte[] image, ColorProps colorProps) {
        this.name = name;
        this.type = type;
        this.image = image;
        this.colorProps = colorProps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public ColorProps getColorProps() {
        return colorProps;
    }

    public void setColorProps(ColorProps colorProps) {
        this.colorProps = colorProps;
    }
}




