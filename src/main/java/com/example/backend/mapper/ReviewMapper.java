package com.example.backend.mapper;

import com.example.backend.pojo.Review;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
}
