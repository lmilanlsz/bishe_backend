package com.example.backend.mapper;

import com.example.backend.pojo.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface BookMapper {
    @Select("select * from book")
    @Results({
            @Result(column = "book_id", property = "book_id"),
            @Result(column = "book_name", property = "book_name"),
            @Result(column = "book_author", property = "book_author")
    })
    ArrayList<Book> getBookList();
}
