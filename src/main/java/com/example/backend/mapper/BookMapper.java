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
    @Select("select b.*, c.category_name from book b join category c where b.category_id = c.category_id ")
    ArrayList<Book> getBookList();

    @Update("update book set book_status = 0 where book_id = #{book_id} ")
    int deleteBook(int book_id);

    @Update("update book set book_id = #{book_id} , book_title = #{book_title}, book_author =#{book_author} ," +
            "category_id = #{category_id}, book_img = #{book_img}, book_rate = #{book_rate}, " +
            "book_rate_num = #{book_rate_num}, book_is_liked = #{book_is_liked}, book_press = #{book_press}, " +
            "book_page = #{book_page}, book_desc = #{book_desc}  where book_id = #{book_id}")
    int updateBook(Book book);

    @Insert("insert into book (book_id, book_title, book_author, category_id, book_img, book_rate, book_page, book_press, book_desc)" +
            " values(#{book_id},#{book_title},#{book_author} ,#{category_id}, #{book_img}, #{book_rate}, #{book_page}, #{book_press}, #{book_desc}   )")
    @Options(useGeneratedKeys = true,keyProperty = "book_id")
    int insertBook(Book book);

    @Select("select book_title, book_rate from book order by book_rate desc limit 7")
    ArrayList<HashMap<String, String>> getBookReport();

    @Select("select book_title, book_is_liked from book order by book_is_liked desc limit 7")
    ArrayList<HashMap<String, String>> getBookLikeReport();

    @Select("select b.category_id, count(*) as count, c.category_name from book b join category c where c.category_id = b.category_id group by b.category_id")
    ArrayList<HashMap<String, String>> getCategoryReport();

    @Update("update book b join review r set b.book_rate =  (b.book_rate * (b.book_rate_num - 1) + #{review_rate})/b.book_rate_num where b.book_id = #{book_id}")
    int updateBookRate(int book_id, Float review_rate);

    @Select("select b.*, c.category_name from book b join category c where c.category_id = b.category_id and book_id = #{book_id} ")
    Book getDetail(int book_id);

    @Update("update book set book_is_liked = book_is_liked + 1 where book_id = #{book_id}")
    int likeBook(int book_id);

    @Update("update book set book_rate_num = book_rate_num + 1 where book_id = #{book_id}")
    int updateRateNum(int book_id);

    @Update("update book set book_rate_num = book_rate_num - 1 where book_id = #{book_id}")
    int decreaseRateNum(int book_id);

    @Select("select * from book order by book_is_liked desc limit 6")
    ArrayList<Book> getBookByRatedNum();

    @Select("select * from book where book_id = #{book_id} ")
    Book getBookById(int book_id);

    @Select("select book_title from book")
    ArrayList<String> getBookTitleList();

    @Select("select b.*, c.category_name from book b join category c where b.category_id = c.category_id and c.category_name = #{category_name}  ")
    ArrayList<Book> getBookByCategory(String category_name);

    //SELECT book_title from book where book_rate BETWEEN 4 and 5
    @Select("select b.*, c.category_name from book b join category c where b.category_id = c.category_id and b.book_rate between 4 and 5")
    ArrayList<Book> getGoodBook();

    @Select("select b.*, c.category_name from book b join category c where b.category_id = c.category_id and b.book_rate between 2 and 4")
    ArrayList<Book> getMidBook();

    @Select("select b.*, c.category_name from book b join category c where b.category_id = c.category_id and b.book_rate < 2")
    ArrayList<Book> getBadBook();
}
