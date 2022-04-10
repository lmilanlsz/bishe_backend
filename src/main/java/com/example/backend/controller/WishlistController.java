package com.example.backend.controller;

import com.example.backend.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("wishlist")
public class WishlistController extends BaseController {
    @PostMapping("read")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> setRead(int wishlist_id) {
        int flag = wishlistService.setRead(wishlist_id);
        Result<String> result = new Result<>();
        if (flag == 1) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("愿望单已读成功");
        } else {
            result.setCode(HttpStatus.NOT_ACCEPTABLE.value());
            result.setMsg("愿望单已读失败");
        }
        return result;
    }

    @PostMapping("delete")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> delete(int wishlist_id) {
        int flag = wishlistService.delete(wishlist_id);
        Result<String> result = new Result<>();
        if (flag == 1) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("愿望单删除成功");
        } else {
            result.setCode(HttpStatus.NOT_ACCEPTABLE.value());
            result.setMsg("愿望单删除失败");
        }
        return result;
    }

    @PostMapping("upload")
    public Result<String> uploadWishlist(int book_id, int user_id) throws Exception {
        int check = wishlistService.checkWishlist(book_id);
        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );
        Result<String> result = new Result<>();
        System.out.println("当前条数" + check);
        if (check == 1) {
            result.setCode(HttpStatus.NOT_FOUND.value());
            result.setMsg("用户已存在！");
        } else {
            if (wishlistService.uploadWishlist(book_id, user_id, date) == 1) {
                result.setMsg("注册成功！");
            } else {
                result.setCode(HttpStatus.NOT_ACCEPTABLE.value());
                result.setMsg("注册失败！");
            }
        }

        return result;
    }
}
