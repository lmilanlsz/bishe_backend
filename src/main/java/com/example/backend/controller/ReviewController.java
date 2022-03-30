package com.example.backend.controller;

import com.example.backend.pojo.Book;
import com.example.backend.pojo.Review;
import com.example.backend.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
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
}
