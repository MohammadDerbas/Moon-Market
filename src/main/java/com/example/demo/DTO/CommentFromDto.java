package com.example.demo.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class CommentFromDto {
    public Long id;
    public String comment;
    public Double rating;
    public String from;
    public LocalDateTime time;

    public CommentFromDto() {
    }

    public CommentFromDto(String comment, String fromCustomer) {
        this.comment = comment;
        this.from = fromCustomer;

    }

    public CommentFromDto(String comment, String fromCustomer,Double rating) {
        this.comment = comment;
        this.from = fromCustomer;
        this.rating=rating;

    }

    public CommentFromDto(Long id, String comment, String from,Double rating) {
        this.id = id;
        this.comment = comment;
        this.from = from;
        this.rating=rating;
    }

    public CommentFromDto(Long id, String comment, String from, LocalDateTime time,Double rating) {
        this.id = id;
        this.comment = comment;
        this.from = from;
        this.time = time;
        this.rating=rating;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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
    public String getFrom() {
        return from;
    }


    public void setFrom(String from) {
        this.from = from;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentFromDto that = (CommentFromDto) o;
        return Objects.equals(comment, that.comment) && Objects.equals(from, that.from) && Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, from,rating);
    }

    @Override
    public String toString() {
        return "CommentFromDto{" +
                "comment='" + comment + '\'' +
                ", from='" + from + '\'' +
                ", rating='" + rating + '\'' +

                '}';
    }
}
