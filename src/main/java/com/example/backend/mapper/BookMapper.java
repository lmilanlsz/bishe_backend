package com.example.backend.mapper;

import com.example.backend.pojo.Book;
import com.example.backend.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

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

    @Select("select book_title, book_rate from book order by book_rate desc limit 7")
    ArrayList<HashMap<String, String>> getBookReport();

    @Update("update book b join review r set b.book_rate =  (b.book_rate * (b.book_rate_num - 1) + r.review_rate)/b.book_rate_num where b.book_id = #{book_id} and r.book_id = #{book_id} ")
    int updateBookRate(int book_id);

    @Select("select * from book where book_id = #{book_id} ")
    Book getDetail(int book_id);
}
