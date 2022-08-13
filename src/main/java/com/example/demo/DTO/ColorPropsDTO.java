package com.example.demo.DTO;

import java.util.Objects;

public class ColorPropsDTO {
    private String color;
    private String images;

    public ColorPropsDTO() {
    }

    public ColorPropsDTO(String color, String images) {
        this.color = color;
        this.images = images;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorPropsDTO that = (ColorPropsDTO) o;
        return Objects.equals(color, that.color) && Objects.equals(images, that.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, images);
    }

    @Override
    public String toString() {
        return "ColorPropsDTO{" +
                "color='" + color + '\'' +
                ", images='" + images + '\'' +
                '}';
    }
}
