package com.example.demo.DTO;

import java.util.Objects;

public class ImageDto {
    public String image;

    public ImageDto(String image) {
        this.image = image;
    }

    public ImageDto() {
    }

    public String getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDto imageDto = (ImageDto) o;
        return Objects.equals(image, imageDto.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image);
    }
}
