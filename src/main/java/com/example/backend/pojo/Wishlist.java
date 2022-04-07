package com.example.backend.pojo;

import java.util.Date;

public class Wishlist {
    public Integer user_id;
    public Integer book_id;
    public String username;
    public String book_title;
    public Integer wishlist_id;
    public Date wish_date;
    public Integer wish_status;

    public Integer getWish_status() {
        return wish_status;
    }

    public void setWish_status(Integer wish_status) {
        this.wish_status = wish_status;
    }

    public Date getWish_date() {
        return wish_date;
    }

    public void setWish_date(Date wish_date) {
        this.wish_date = wish_date;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public Integer getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(Integer wishlist_id) {
        this.wishlist_id = wishlist_id;
    }
}
