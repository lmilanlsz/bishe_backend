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
}
