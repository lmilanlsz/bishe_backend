package com.example.backend.mapper;

import com.example.backend.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface CategoryMapper {

    @Select("select * from category")
    ArrayList<Category> getCategoryList();

    @Select("select distinct(c.category_name), c.category_id, c.category_value, b.category_id from category c JOIN book b where b.category_id = c.category_id ")
    ArrayList<Category> getUsedCategoryList();

    @Insert("insert into category(category_id, category_name, category_value) values(#{category_id}, #{category_name}, #{category_value}  )")
    int insertCategory(Category category);
}
