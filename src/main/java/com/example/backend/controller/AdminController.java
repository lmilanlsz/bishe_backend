package com.example.backend.controller;

import com.example.backend.pojo.User;
import com.example.backend.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("admin")
public class AdminController extends BaseController {
    @GetMapping("list")
    public Result<ArrayList<User>> getUserList() {
        Result<ArrayList<User>> result = new Result<>();
        ArrayList<User> userlist = userService.getUserList();
        System.out.println("暂停");
        result.setData(userlist);
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取记录成功");
        return result;
    }

    @GetMapping("delete")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> deleteGoods(int no){
        int flag = userService.deleteUser(no);
        Result<String> result = new Result<>();
        if (flag == 1) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("用户删除成功");
        } else {
            result.setCode(HttpStatus.NOT_ACCEPTABLE.value());
            result.setMsg("用户删除失败");
        }
        return result;
    }

    @PostMapping("/upload")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> uploadUser(){
        User user = new User();
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        user.setUser_id(user_id);
        user.setUsername(request.getParameter("username"));
        user.setUser_pwd(request.getParameter("user_pwd"));
        user.setIs_admin(Integer.parseInt(request.getParameter("is_admin")));
        int flag;
        if(user_id != 0){
            //update操作
            System.out.println("开始更新操作");
            flag = userService.updateUser(user);
        }else{
            //add操作
            System.out.println("开始插入操作");
            flag = userService.insertUser(user);
        }
        System.out.println("插入操作完毕");
        System.out.println(user_id);
        Result<String> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMsg("用户信息更新成功");
        result.setData("用户信息更新成功");
        return result;
    }
}
