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
        int n = reviews1.size();

        List<Float> reviews1RateCollect = reviews1.stream().map(A -> A.review_rate).collect(Collectors.toList());
        List<Float> reviews2RateCollect = reviews2.stream().map(A -> A.review_rate).collect(Collectors.toList());

        double Ex = reviews1RateCollect.stream().mapToDouble(x -> x).sum();
        double Ey = reviews2RateCollect.stream().mapToDouble(y -> y).sum();
        double Ex2 = reviews1RateCollect.stream().mapToDouble(x -> Math.pow(x, 2)).sum();
        double Ey2 = reviews2RateCollect.stream().mapToDouble(y -> Math.pow(y, 2)).sum();
        double Exy = IntStream.range(0, n).mapToDouble(i -> reviews1RateCollect.get(i)).sum();

        double numerator = Exy - Ex * Ey / n;
        double denominator = Math.sqrt((Ex2 - Math.pow(Ex, 2) / n) * (Ey2 - Math.pow(Ey, 2) / n));
        if (denominator == 0) return 0.0;
        return numerator / denominator;
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
