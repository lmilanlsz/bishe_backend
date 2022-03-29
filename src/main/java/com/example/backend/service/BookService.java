package com.example.backend.service;

import com.example.backend.mapper.BookMapper;
import com.example.backend.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("bookService")
public class BookService {
    @Autowired
    BookMapper bookMapper;

    public ArrayList<Book> getBookList() {
        return bookMapper.getBookList();
    }
}
