package com.example.backend.pojo;

import java.util.Date;

public class Share {
    public Integer share_id;
    public Integer user_id;
    public Integer book_id;
    public String username;
    public String book_title;
    public Date share_date;

    public Integer getShare_id() {
        return share_id;
    }

    public void setShare_id(Integer share_id) {
        this.share_id = share_id;
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

    public Date getShare_date() {
        return share_date;
    }

    public void setShare_date(Date share_date) {
        this.share_date = share_date;
    }
}
