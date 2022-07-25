package com.example.demo.DTO;

import java.util.Objects;

public class CommentFromDto {
    public String comment;
    public String from;

    public CommentFromDto() {
    }

    public CommentFromDto(String comment, String fromCustomer) {
        this.comment = comment;
        this.from = fromCustomer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentFromDto that = (CommentFromDto) o;
        return Objects.equals(comment, that.comment) && Objects.equals(from, that.from);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, from);
    }

    @Override
    public String toString() {
        return "CommentFromDto{" +
                "comment='" + comment + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
