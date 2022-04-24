package com.example.backend.controller;

import com.example.backend.pojo.Book;
import com.example.backend.pojo.Category;
import com.example.backend.service.CategoryService;
import com.example.backend.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("category")
public class CategoryController extends BaseController {
    @GetMapping("list")
    public Result<ArrayList<Category>> getCategoryList() {
        System.out.println("开始获取");
        Result<ArrayList<Category>> result = new Result<>();
        ArrayList<Category> categorylist = categoryService.getCategoryList();
        System.out.println("暂停");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(categorylist);
        return result;
    }

    @GetMapping("usedList")
    public Result<ArrayList<Category>> getUsedCategoryList() {
        System.out.println("开始获取");
        Result<ArrayList<Category>> result = new Result<>();
        ArrayList<Category> categorylist = categoryService.getUsedCategoryList();
        System.out.println("暂停");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(categorylist);
        return result;
    }

    @PostMapping("insert")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> insertCategory() {
        Result<String> result = new Result<>();
        Category category = new Category();
        String category_id = request.getParameter("category_id");
        category.setCategory_id(category_id);
        category.setCategory_name(request.getParameter("category_name"));
        category.setCategory_value(request.getParameter("category_value"));
        System.out.println("开始插入新类别");
        int flag = categoryService.insertCategory(category);
        if (flag == 1) {
            System.out.println(category);
            result.setCode(HttpStatus.OK.value());
            result.setMsg("图书类别插入成功");
            result.setData("图书类别插入成功");
        }
        return result;
    }
}
