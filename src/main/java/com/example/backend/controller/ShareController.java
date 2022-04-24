package com.example.backend.controller;

import com.example.backend.mapper.ShareMapper;
import com.example.backend.pojo.Book;
import com.example.backend.pojo.Review;
import com.example.backend.pojo.Share;
import com.example.backend.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("share")
public class ShareController extends BaseController {
    @PostMapping("/new")
    public Result<String> newShare() throws Exception {
        Share share = new Share();

        Date date = new Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        Date time = sdf.parse( nowTime );

        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int book_id = Integer.parseInt(request.getParameter("book_id"));
        share.setUser_id(user_id);
        share.setBook_id(book_id);
        share.setShare_date(time);

        System.out.println("开始插入操作");
        int flag = shareService.insertShare(share);

        Result<String> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMsg("评价信息更新成功");
        result.setData("评价信息更新成功");
        return result;
    }

    @PostMapping("list")
    public Result<ArrayList<Share>> getBookList(int book_id) {
        System.out.println("开始获取");
        Result<ArrayList<Share>> result = new Result<>();
        ArrayList<Share> sharelist = shareService.getShareList(book_id);
        System.out.println("暂停");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(sharelist);
        return result;
    }

    @PostMapping("delete")
    public Result<String> deleteShre(int share_id) {
        System.out.println("开始获取");
        Result<String> result = new Result<>();
        int book_id = shareService.getBook_id(share_id);
        int num = bookService.decreaseRateNum(book_id);
        int flag = shareService.deleteShare(share_id);
        System.out.println("暂停");
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData("删除成功！");
        return result;
    }
}
