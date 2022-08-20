package com.example.demo.DTO;

import java.util.Objects;

public class CommentDTO {
    private String comment;
    public Double rating;
    public CommentDTO() {
    }

    public CommentDTO(String comment,Double rating) {
        this.comment = comment;
        this.rating=rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getRating(){return this.rating;}
    public void setRating(Double rating){
        this.rating=rating;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO that = (CommentDTO) o;
        return Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment);
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "comment='" + comment  +
                '}';
    }
}
