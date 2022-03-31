package com.example.backend.mapper;

import com.example.backend.pojo.Book;
import com.example.backend.pojo.Review;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface ReviewMapper {
    @Select("select r.user_id,r.book_id, r.review_id, r.review_content, r.review_rate, b.book_title, u.username from review r  " +
            "join user u on r.user_id = u.user_id join book b on r.book_id = b.book_id ")
    ArrayList<Review> getReviewList();

    @Delete("delete from review where review_id = #{review_id}")
    int deleteReview(int review_id);

    @Update("update review set user_id = #{user_id} , review_id = #{review_id}, review_rate = #{review_rate}, " +
            "review_content = #{review_content}, book_id = #{book_id} where review_id = #{review_id}")
    int updateReview(Review review);

    @Insert("insert into review (user_id, review_id, review_rate, review_content, book_id)" +
            " values(#{user_id}, #{review_id}, #{review_rate}, #{review_content}, #{book_id}  )")
    @Options(useGeneratedKeys = true,keyProperty = "no")
    int insertReview(Review review);
}
