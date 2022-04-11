//package com.example.backend.main;
//
//import com.example.backend.controller.RecommendController;
//import com.example.backend.controller.ReviewController;
//import com.example.backend.pojo.Book;
//import com.example.backend.pojo.Review;
//
//import java.util.ArrayList;
//
//public class Demo {
//    public static void main(String[] args) {
//        ReviewController reviewController = new ReviewController();
//        ArrayList<Review> reviews = reviewController.getAllReview();
//        RecommendController recommendController = new RecommendController();
//        ArrayList<Book> res = recommendController.recommend(1, reviews);
//        System.out.println("结果如下：");
//        for (Book book : res) {
//            System.out.println("书名：" + book.book_title);
//        }
//    }
//}
