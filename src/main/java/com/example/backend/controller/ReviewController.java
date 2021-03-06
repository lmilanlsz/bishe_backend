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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    @PostMapping("byTitle")
    public Result<ArrayList<Review>> getReviewListByTitle() {
        Result<ArrayList<Review>> result = new Result<>();
        String book_title = request.getParameter("book_title");
        ArrayList<Review> reviewlist = reviewService.getReviewListByTitle(book_title);
        System.out.println("暂停");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(reviewlist);
        return result;
    }

    @PostMapping("byUser")
    public Result<ArrayList<Review>> getReviewListByUsername() {
        Result<ArrayList<Review>> result = new Result<>();
        String username = request.getParameter("username");
        ArrayList<Review> reviewlist = reviewService.getReviewListByUsername(username);
        System.out.println("暂停");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(reviewlist);
        return result;
    }

    @PostMapping("byRate")
    public Result<ArrayList<Review>> getReviewListByRate() {
        Result<ArrayList<Review>> result = new Result<>();
        Float review_rate = Float.parseFloat(request.getParameter("review_rate"));
        ArrayList<Review> reviewlist = reviewService.getReviewListByRate(review_rate);
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
            result.setMsg("评价屏蔽成功");
        } else {
            result.setCode(HttpStatus.NOT_ACCEPTABLE.value());
            result.setMsg("评价屏蔽失败");
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
            flag = bookService.updateBookRate(review.book_id, review.review_rate);
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

    @PostMapping("/byBook")
    public Result<ArrayList<Review>> getReviewByBook(int book_id) throws Exception {
        Result<ArrayList<Review>> result = new Result<>();
        ArrayList<Review> reviewlist = reviewService.getReviewListByBook(book_id);
        System.out.println(reviewlist.size());
        if (reviewlist.size() == 0){
            result.setData(reviewlist);
            result.setCode(HttpStatus.NOT_FOUND.value());
            System.out.println("获得0条评价");
            result.setMsg("获得0条评价");
        } else {
            result.setData(reviewlist);
            result.setCode(HttpStatus.OK.value());
            result.setMsg("获取该图书评价成功");
        }
        return result;
    }

    @PostMapping("/myReview")
    public Result<Review> getMyReview(int book_id, int user_id) throws Exception {
        Result<Review> result = new Result<>();
        Review review = reviewService.getMyReview(book_id, user_id);
        System.out.println("评价已获取");
        result.setData(review);
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取记录成功");
        return result;
    }

    @PostMapping("/new")
    public Result<String> newReview() throws Exception {
        Review review = new Review();

        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );

        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int book_id = Integer.parseInt(request.getParameter("book_id"));
        review.setUser_id(user_id);
        review.setReview_rate(Float.parseFloat(request.getParameter("review_rate")));
        review.setReview_content(request.getParameter("review_content"));
        review.setBook_id(book_id);
        review.setReview_date(time);

        System.out.println("开始插入操作");
        int flag = reviewService.insertReview(review);

        if (flag == 1) {
            int rateNum = bookService.updateRateNum(book_id);
            int recalculate = bookService.updateBookRate(book_id, review.review_rate);
        }

        Result<String> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMsg("评价信息更新成功");
        result.setData("评价信息更新成功");
        return result;
    }

    public ArrayList<Review> getAllReview() {
         return reviewService.getAllReview();
    }

    @GetMapping("/title")
    public Result<ArrayList<String>> getBookTitleList() {
        Result<ArrayList<String>> result = new Result<>();
        ArrayList<String> titlelist = bookService.getBookTitleList();
        result.setData(titlelist);
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取图书标题成功");
        return result;
    }

    @GetMapping("/user")
    public Result<ArrayList<String>> getUsernameList() {
        Result<ArrayList<String>> result = new Result<>();
        ArrayList<String> userlist = userService.getUsernameList();
        result.setData(userlist);
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取图书标题成功");
        return result;
    }
}
