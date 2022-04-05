package com.example.backend.controller;

import com.example.backend.pojo.Book;
import com.example.backend.pojo.Review;
import com.example.backend.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("review")
public class ReviewController extends BaseController {
    @GetMapping("list")
    public Result<ArrayList<Review>> getReviewList() {
        System.out.println("开始获取");
        Result<ArrayList<Review>> result = new Result<>();
        ArrayList<Review> reviewlist = reviewService.getReviewList();
        System.out.println("暂停");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(reviewlist);
        return result;
    }

    @GetMapping("delete")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> deleteReview(int no){
        int flag = reviewService.deleteReview(no);
        Result<String> result = new Result<>();
        if (flag == 1) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("评价删除成功");
        } else {
            result.setCode(HttpStatus.NOT_ACCEPTABLE.value());
            result.setMsg("评价删除失败");
        }
        return result;
    }

    @PostMapping("/upload")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> uploadReview(){
        Review review = new Review();
        int review_id = Integer.parseInt(request.getParameter("review_id"));
        review.setReview_id(review_id);
        review.setUser_id(Integer.parseInt(request.getParameter("user_id")));
        review.setReview_rate(Float.parseFloat(request.getParameter("review_rate")));
        review.setReview_content(request.getParameter("review_content"));
        review.setBook_id(Integer.parseInt(request.getParameter("book_id")));
        int flag;
        if(review_id != 0){
            //update操作
            System.out.println("开始更新操作");
            flag = reviewService.updateReview(review);
            flag = bookService.updateBookRate(review.book_id);
        }else{
            //add操作
            System.out.println("开始插入操作");
            flag = reviewService.insertReview(review);
        }
        System.out.println("插入操作完毕");
        System.out.println(review);
        Result<String> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMsg("评价信息更新成功");
        result.setData("评价信息更新成功");
        return result;
    }
}
