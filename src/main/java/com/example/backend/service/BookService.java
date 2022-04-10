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

    public int updateBookRate(int book_id) {
        return bookMapper.updateBookRate(book_id);
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
}
