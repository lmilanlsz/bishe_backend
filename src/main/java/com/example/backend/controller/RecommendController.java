package com.example.backend.controller;

import com.example.backend.pojo.Book;
import com.example.backend.pojo.Review;
import com.example.backend.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.events.Event;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("recommend")
public class RecommendController extends BaseController {
    public HashMap<Double, Integer> computeNearestNeighbor(int user_id, ArrayList<Review> reviews) {
        HashMap<Double, Integer> distances = new HashMap<>();

        ArrayList<Review> r1list = reviewService.getReviewListByUser(user_id);

        Review r1 = new Review(user_id);

        for (Review review : reviews) {
            if (user_id == review.user_id) {
                r1 = new Review();
            }
        }

        for (int i = 0; i < reviews.size(); i++) {
            Review r2 = reviews.get(i);
            ArrayList<Review> r2list = reviewService.getReviewListByUser(r2.user_id);

            if (!r2.user_id.equals(user_id)) {
                double distance = pearson_dis(r1list, r2list);
                distances.put(distance, r2.user_id);
            }
        }

        System.out.println("皮尔森系数：" + distances);
        return distances;
    }

    public double pearson_dis(List<Review> reviews1, List<Review> reviews2) {
        float sum_xy = 0;
        float sum_x = 0;
        float sum_y = 0;
        double sum_x2 = 0;
        double sum_y2 = 0;
        int n = 0;
        for (int i = 0; i < reviews1.size(); i++) {
            Review review1 = reviews1.get(i);
            for (int j = 0; j < reviews2.size(); j++) {
                Review review2 = reviews2.get(j);
                if (review1.book_id == review2.book_id) {
                    n++;
                    float x = review1.review_rate;
                    float y = review2.review_rate;
                    sum_xy += x * y;
                    sum_x += x;
                    sum_y += y;
                    sum_x2 += Math.pow(x, 2);
                    sum_y2 += Math.pow(y, 2);
                }
            }
        }

        double denominator = Math.sqrt(sum_x2 - Math.pow(sum_x, 2) / n) * Math.sqrt(sum_y2 - Math.pow(sum_y, 2) / n);
        if (denominator == 0) {
            return  0;
        } else {
            return (sum_xy - (sum_x * sum_y) / n) / denominator;
        }
    }

    @PostMapping("res")
    public ArrayList<Book> recommend(int user_id) {
        ArrayList<Review> reviews = reviewService.getAllReview();
        HashMap<Double, Integer> distances = computeNearestNeighbor(user_id, reviews);
        Integer nearest = distances.values().iterator().next();
        System.out.println("最近邻：" + nearest);

        Review neighborReviews = new Review();
        for (Review review : reviews) {
            if (nearest == review.user_id) {
                neighborReviews = review;
            }
        }
        System.out.println("邻居看的书：" + reviewService.getBookIdByUserId(neighborReviews.user_id));

        Review reviewRating = new Review();
        for (Review review : reviews) {
            if (user_id == review.user_id) {
                reviewRating = review;
            }
        }
        System.out.println("用户看的书：" + reviewService.getBookIdByUserId(reviewRating.user_id));

        ArrayList<Book> recommendation = new ArrayList<>();
        for (int book_id : reviewService.getBookIdByUserId(neighborReviews.user_id)) {
            if (reviewRating.find(book_id) == null) {
                recommendation.add(bookService.getBookById(book_id));
            }
        }

        return recommendation;
    }
}
