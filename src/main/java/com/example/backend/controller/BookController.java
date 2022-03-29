package com.example.backend.controller;

import com.example.backend.pojo.Book;
import com.example.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("book")
public class BookController extends BaseController {
    @GetMapping("list")
    public Result<ArrayList<Book>> getBookList() {
        Result<ArrayList<Book>> result = new Result<>();
        ArrayList<Book> booklist = bookService.getBookList();
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(booklist);
        return result;
    }
}
