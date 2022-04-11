package com.example.backend.service;

import com.example.backend.mapper.ReviewMapper;
import com.example.backend.pojo.Book;
import com.example.backend.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ReviewService {
    @Autowired
    ReviewMapper reviewMapper;

    public ArrayList<Review> getReviewList() {
        return reviewMapper.getReviewList();
    }

    public int deleteReview(int review_id) {
        return reviewMapper.deleteReview(review_id);
    }

    public int updateReview(Review review) {
        return reviewMapper.updateReview(review);
    }

    public int insertReview(Review review) {
        return reviewMapper.insertReview(review);
    }

    public ArrayList<Review> getReviewListByUser(int user_id) {
        return reviewMapper.getReviewListByUser(user_id);
    }

    public ArrayList<Review> getReviewListByBook(int book_id) {
        return reviewMapper.getReviewByBook(book_id);
    }

    public Review getMyReview(int book_id, int user_id) {
        return reviewMapper.getMyReview(book_id, user_id);
    }

    public ArrayList<Review> getAllReview() {
        return reviewMapper.getAllReview();
    }

    public ArrayList<Integer> getBookIdByUserId(int user_id) {
        return reviewMapper.getBookIdByUserId(user_id);
    }
}
