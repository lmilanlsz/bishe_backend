package com.example.backend.pojo;

public class Book {
    public Integer book_id;
    public String book_title;
    public float book_rate;
    public String book_author;
    public String category_id;
    public String category_name;
    public Integer book_is_liked;
    public String book_img;
    public Integer book_status;
    public Integer book_rate_num;
    public String book_press;
    public String book_desc;
    public Integer book_page;



    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getBook_press() {
        return book_press;
    }

    public void setBook_press(String book_press) {
        this.book_press = book_press;
    }

    public String getBook_desc() {
        return book_desc;
    }

    public void setBook_desc(String book_desc) {
        this.book_desc = book_desc;
    }

    public Integer getBook_page() {
        return book_page;
    }

    public void setBook_page(Integer book_page) {
        this.book_page = book_page;
    }

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
