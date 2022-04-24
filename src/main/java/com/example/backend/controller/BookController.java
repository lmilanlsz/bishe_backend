package com.example.backend.controller;

import com.example.backend.pojo.Book;
import com.example.backend.pojo.User;
import com.example.backend.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("book")
public class BookController extends BaseController {
    @GetMapping("list")
    public Result<ArrayList<Book>> getBookList() {
        System.out.println("开始获取");
        Result<ArrayList<Book>> result = new Result<>();
        ArrayList<Book> booklist = bookService.getBookList();
        System.out.println("暂停");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(booklist);
        return result;
    }

    @GetMapping("delete")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> deleteBook(int no){
        int flag = bookService.deleteBook(no);
        Result<String> result = new Result<>();
        if (flag == 1) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("图书删除成功");
        } else {
            result.setCode(HttpStatus.NOT_ACCEPTABLE.value());
            result.setMsg("图书删除失败");
        }
        return result;
    }

    @PostMapping("/upload")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> uploadBook(){
        Book book = new Book();
        int flag = Integer.parseInt(request.getParameter("is_add"));
        System.out.println(flag);
        int book_id = Integer.parseInt(request.getParameter("book_id"));
        book.setBook_id(book_id);
        book.setBook_rate_num(Integer.parseInt(request.getParameter("book_rate_num")));
        book.setBook_is_liked(Integer.parseInt(request.getParameter("book_is_liked")));
        book.setBook_page(Integer.parseInt(request.getParameter("book_page")));
        System.out.println(book.book_is_liked);
        book.setBook_rate(Float.parseFloat(request.getParameter("book_rate")));
        book.setBook_img(request.getParameter("book_img"));
        book.setBook_title(request.getParameter("book_title"));
        book.setBook_author(request.getParameter("book_author"));
        book.setCategory_id(request.getParameter("category_id"));
        book.setBook_desc(request.getParameter("book_desc"));
        book.setBook_press(request.getParameter("book_press"));
        if(flag == 0){
            //update操作
            System.out.println("开始更新操作");
            int op = bookService.updateBook(book);
        }else{
            //add操作
            System.out.println("开始插入操作");
            int op = bookService.insertBook(book);
        }
        System.out.println("插入操作完毕");
        System.out.println(book);
        Result<String> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMsg("用户信息更新成功");
        result.setData("用户信息更新成功");
        return result;
    }

    @GetMapping("report/rate")
    public Result<ArrayList<String[]>> getBookReport() {
        Result<ArrayList <String[]>> result = new Result<>();
        ArrayList<HashMap<String, String >> mapList = bookService.getBookReport();
        ArrayList <String[]> data = new ArrayList<>();
//        data.add(new String []{"商品","销售总量","销售总额","净利润"});
        for (HashMap<String, String> map:mapList) {
            data.add(new String[]{map.get("book_title"), String.valueOf(map.get("book_rate"))});
        }
        result.setData(data);
        result.setCode(HttpStatus.OK.value());
        return result;
    }

    @GetMapping("report/like")
    public Result<ArrayList<String[]>> getBookLikeReport() {
        Result<ArrayList <String[]>> result = new Result<>();
        ArrayList<HashMap<String, String>> mapList = bookService.getBookLikeReport();
        ArrayList <String[]> data = new ArrayList<>();
//        data.add(new String []{"商品","销售总量","销售总额","净利润"});
        for (HashMap<String, String> map:mapList) {
            data.add(new String[]{map.get("book_title"), String.valueOf(map.get("book_is_liked"))});
        }
        result.setData(data);
        result.setCode(HttpStatus.OK.value());
        return result;
    }

    @GetMapping("report/cate")
    public Result<ArrayList<String[]>> getCategoryReport() {
        Result<ArrayList <String[]>> result = new Result<>();
        ArrayList<HashMap<String, String>> mapList = bookService.getCategoryReport();
        ArrayList <String[]> data = new ArrayList<>();
        for (HashMap<String, String> map:mapList) {
            data.add(new String[]{map.get("category_name"), String.valueOf(map.get("count"))});
        }
        result.setData(data);
        result.setCode(HttpStatus.OK.value());
        return result;
    }

    @PostMapping("/detail")
    public Result<Book> getDetail(int book_id) {
        Result<Book> result = new Result<>();
        Book book = bookService.getDetail(book_id);
        System.out.println("图书详情已获取");
        result.setData(book);
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取记录成功");
        return result;
    }

    @PostMapping("like")
    public Result<String> likeBook(int book_id) throws Exception {
        Result<String> result = new Result<>();
        int flag = bookService.likeBook(book_id);
        System.out.println("图书详情已获取");
        result.setData("推荐成功");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取记录成功");
        return result;
    }

    @GetMapping("byRateNum")
    public Result<ArrayList<Book>> getBookRateNum() throws Exception {
        Result<ArrayList<Book>> result = new Result<>();
        ArrayList<Book> booklist = bookService.getBookByRatedNum();
        System.out.println("暂停");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(booklist);
        return result;
    }

    @PostMapping("byCate")
    public Result<ArrayList<Book>> getBookByCategory(String category_name) throws Exception {
        Result<ArrayList<Book>> result = new Result<>();
        ArrayList<Book> booklist = bookService.getBookByCategory(category_name);
        System.out.println("暂停");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(booklist);
        return result;
    }

    @PostMapping("byRate")
    public Result<ArrayList<Book>> getBookByRate(Float book_rate) {
        ArrayList<Book> booklist = new ArrayList<>();
        if (book_rate == 1) {
            booklist = bookService.getGoodBook();
        } else if (book_rate == 2) {
            booklist = bookService.getMidBook();
        } else if (book_rate == 3) {
            booklist = bookService.getBadBook();
        }
        Result<ArrayList<Book>> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(booklist);
        return result;
    }
}
