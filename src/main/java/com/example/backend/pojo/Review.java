package com.example.backend.pojo;

import java.util.ArrayList;
import java.util.Date;

public class Review {
    public Integer review_id;
    public float review_rate;
    public Integer user_id;
    public Integer book_id;
    public String review_content;
    public Date review_date;
    public String book_title;
    public String username;
    public String category_name;
    public Integer review_status;
    public ArrayList<Book> booklist = new ArrayList<>();

    public Review() {}

    public Review(int user_id) {
        this.user_id = user_id;
    }

    public Book find(Integer book_id) {
        for (Book book : booklist) {
            if (book_id == book.book_id) {
                return book;
            }
        }
        return null;
    }

    public Integer getReview_status() {
        return review_status;
    }

    public void setReview_status(Integer review_status) {
        this.review_status = review_status;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public ArrayList<Book> getBooklist() {
        return booklist;
    }

    public void setBooklist(ArrayList<Book> booklist) {
        this.booklist = booklist;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public float getReview_rate() {
        return review_rate;
    }

    public void setReview_rate(float review_rate) {
        this.review_rate = review_rate;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public Date getReview_date() {
        return review_date;
    }

    public void setReview_date(Date review_date) {
        this.review_date = review_date;
    }
}
