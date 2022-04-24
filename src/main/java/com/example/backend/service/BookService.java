package com.example.backend.service;

import com.example.backend.mapper.BookMapper;
import com.example.backend.pojo.Book;
import com.example.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;

    public ArrayList<Book> getBookList() {
        return bookMapper.getBookList();
    }

    public int deleteBook(int book_id) {
        return bookMapper.deleteBook(book_id);
    }

    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    public int insertBook(Book book) {
        return bookMapper.insertBook(book);
    }

    public ArrayList<HashMap<String, String>> getBookReport() {
        return bookMapper.getBookReport();
    }

    public ArrayList<HashMap<String, String>> getBookLikeReport() {
        return bookMapper.getBookLikeReport();
    }

    public ArrayList<HashMap<String, String>> getCategoryReport() {
        return bookMapper.getCategoryReport();
    }

    public int updateBookRate(int book_id, Float review_rate) {
        return bookMapper.updateBookRate(book_id, review_rate);
    }

    public Book getDetail(int book_id) {
        return bookMapper.getDetail(book_id);
    }

    public int likeBook(int book_id) {
        return bookMapper.likeBook(book_id);
    }

    public int updateRateNum(int book_id) {
        return bookMapper.updateRateNum(book_id);
    }

//    decreaseRateNum
    public int decreaseRateNum(int book_id) {
    return bookMapper.decreaseRateNum(book_id);
}

    public ArrayList<Book> getBookByRatedNum() {
        return bookMapper.getBookByRatedNum();
    }

    public Book getBookById(int book_id) {
        return bookMapper.getBookById(book_id);
    }

    public ArrayList<String> getBookTitleList() {
        return bookMapper.getBookTitleList();
    }

    public ArrayList<Book> getBookByCategory(String category_name) {
        return bookMapper.getBookByCategory(category_name);
    }

    public ArrayList<Book> getGoodBook() {
        return bookMapper.getGoodBook();
    }

    public ArrayList<Book> getMidBook() {
        return bookMapper.getMidBook();
    }

    public ArrayList<Book> getBadBook() {
        return bookMapper.getBadBook();
    }
}
