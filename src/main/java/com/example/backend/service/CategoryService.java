package com.example.backend.service;

import com.example.backend.mapper.CategoryMapper;
import com.example.backend.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public ArrayList<Category> getCategoryList() {
        return categoryMapper.getCategoryList();
    }

    public ArrayList<Category> getUsedCategoryList() {
        return categoryMapper.getUsedCategoryList();
    }

    public int insertCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }
}
