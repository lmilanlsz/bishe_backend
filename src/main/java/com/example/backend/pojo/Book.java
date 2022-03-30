package com.example.backend.pojo;

public class Book {
    public Integer book_id;
    public String book_title;
    public float book_rate;
    public String book_author;
    public String book_category;
    public Integer book_is_liked;
    public String book_img;
    public Integer book_status;
    public Integer book_rate_num;

    public Integer getBook_rate_num() {
        return book_rate_num;
    }

    public void setBook_rate_num(Integer book_rate_num) {
        this.book_rate_num = book_rate_num;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public float getBook_rate() {
        return book_rate;
    }

    public void setBook_rate(float book_rate) {
        this.book_rate = book_rate;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_category() {
        return book_category;
    }

    public void setBook_category(String book_category) {
        this.book_category = book_category;
    }

    public Integer getBook_is_liked() {
        return book_is_liked;
    }

    public void setBook_is_liked(Integer book_is_liked) {
        this.book_is_liked = book_is_liked;
    }

    public String getBook_img() {
        return book_img;
    }

    public void setBook_img(String book_img) {
        this.book_img = book_img;
    }

    public Integer getBook_status() {
        return book_status;
    }

    public void setBook_status(Integer book_status) {
        this.book_status = book_status;
    }
}
