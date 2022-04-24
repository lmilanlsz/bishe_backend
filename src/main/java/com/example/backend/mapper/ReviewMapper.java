package com.example.backend.mapper;

import com.example.backend.pojo.Book;
import com.example.backend.pojo.Review;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface ReviewMapper {
    @Select("select r.user_id, r.book_id, r.review_id, r.review_content, r.review_rate, r.review_date, r.review_status, b.category_id, b.book_title, u.username, c.category_name from review r  " +
            "join user u on r.user_id = u.user_id join book b on r.book_id = b.book_id join category c on c.category_id = b.category_id ")
    ArrayList<Review> getReviewList();

    @Update("update review set review_status = 1 where review_id = #{review_id}")
    int deleteReview(int review_id);

    @Update("update review set user_id = #{user_id} , review_id = #{review_id}, review_rate = #{review_rate}, " +
            "review_content = #{review_content}, book_id = #{book_id} where review_id = #{review_id}" )
    int updateReview(Review review);

    @Insert("insert into review (user_id, review_rate, review_content, book_id, review_date)" +
            " values(#{user_id}, #{review_rate}, #{review_content}, #{book_id}, #{review_date}   )")
    int insertReview(Review review);

    @Select("select r.user_id,r.book_id, r.review_id, r.review_content, r.review_rate, r.review_date, b.book_title, u.username from review r  " +
            "join user u on r.user_id = u.user_id join book b on r.book_id = b.book_id where r.user_id = #{user_id} and r.review_status != 1")
    ArrayList<Review> getReviewListByUser(int user_id);

    @Select("select r.review_rate, r.review_content, r.review_date, u.username from review r  " +
            "join user u on r.user_id = u.user_id where r.book_id = #{book_id} and r.review_status != 1")
    ArrayList<Review> getReviewByBook(int book_id);

    @Select("select r.review_rate, r.review_content, u.username from review r  " +
            "join user u on r.user_id = u.user_id where r.book_id = #{book_id} and r.user_id = #{user_id}")
    Review getMyReview(int book_id, int user_id);

    @Select("select * from review")
    ArrayList<Review> getAllReview();

    @Select("select book_id from review where user_id = #{user_id}")
    ArrayList<Integer> getBookIdByUserId(int user_id);

    @Select("select r.user_id, r.book_id, r.review_id, r.review_content, r.review_rate, r.review_date, r.review_status, b.category_id, b.book_title, u.username, c.category_name from review r  " +
            "join user u on r.user_id = u.user_id join book b on r.book_id = b.book_id join category c on c.category_id = b.category_id  where b.book_title = #{book_title} ")
    ArrayList<Review> getReviewListByTitle(String book_title);

    @Select("select r.user_id, r.book_id, r.review_id, r.review_content, r.review_rate, r.review_date, r.review_status, b.category_id, b.book_title, u.username, c.category_name from review r  " +
            "join user u on r.user_id = u.user_id join book b on r.book_id = b.book_id join category c on c.category_id = b.category_id  where u.username = #{username} ")
    ArrayList<Review> getReviewListByUsername(String username);

    @Select("select r.user_id, r.book_id, r.review_id, r.review_content, r.review_rate, r.review_date, r.review_status, b.category_id, b.book_title, u.username, c.category_name from review r  " +
            "join user u on r.user_id = u.user_id join book b on r.book_id = b.book_id join category c on c.category_id = b.category_id  where r.review_rate > #{review_rate}  ")
    ArrayList<Review> getReviewListByRate(Float review_rate);
}
