package com.example.backend.mapper;

import com.example.backend.pojo.Book;
import com.example.backend.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface BookMapper {
    @Select("select * from book")
//    @Results({
//            @Result(column = "book_id", property = "book_id"),
//            @Result(column = "book_name", property = "book_name"),
//            @Result(column = "book_author", property = "book_author")
//    })
    ArrayList<Book> getBookList();

    @Update("update book set book_status = 0 where book_id = #{book_id} ")
    int deleteBook(int book_id);

    @Update("update book set book_id = #{book_id} , book_title = #{book_title}, book_author =#{book_author} ," +
            "book_category = #{book_category} where book_id = #{book_id}")
    int updateBook(Book book);

    @Insert("insert into book (book_id, book_title, book_author, book_category)" +
            " values(#{book_id},#{book_title},#{book_author} ,#{book_category} )")
    @Options(useGeneratedKeys = true,keyProperty = "no")
    int insertBook(Book book);
}
