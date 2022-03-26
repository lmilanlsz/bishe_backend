package com.example.backend.controller;

import com.example.backend.pojo.User;
import com.example.backend.util.JwtUtil;
import com.example.backend.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @PostMapping("/login")
    public Result<User> login(User user) throws Exception {
        //初始化返回值
        Result<User> result = new Result<>();
        //用户登录校验
        User loginUser = userService.login(user);

        if (loginUser.getIs_admin() == 1) {
            result.setCode(HttpStatus.CONTINUE.value());
            result.setData(loginUser);
            System.out.println(loginUser.getUser_id());
        } else {
            //没有抛出异常表示正常 
            result.setCode(HttpStatus.OK.value());
            result.setData(loginUser);
            System.out.println(loginUser.getUser_id());
        }

//        //没有抛出异常表示正常
//        result.setCode(HttpStatus.OK.value());
//        result.setData(loginUser);

        //声明payload
        Map<String, String> payload = new HashMap<>(2);

        //初始化payload
        payload.put("id", loginUser.getUser_id().toString());
        payload.put("username", loginUser.getUsername());

        //获取令牌
        String token = JwtUtil.getToken(payload);

        //在响应结果中添加token
        result.setMsg(token);

        //返回结果
        return result;
    }

    @PostMapping("/refresh_token")
    public Result<String> refreshToken(String username) throws Exception {

        Map<String, String> payload = new HashMap<>(2);

        //初始化payload
        payload.put("id", userService.getUserIdByName(username));
        payload.put("username",username);


        //获取令牌
        String token = JwtUtil.getToken(payload);
        Result<String> result = new Result<>();
        //在响应结果中添加token
        result.setCode(HttpStatus.OK.value());
        result.setMsg("token刷新成功");
        result.setData(token);
        //返回结果
        return result;
    }

    @GetMapping("/list")
    public Result<List<User>> userList() throws Exception {

        //初始化返回值
        Result<List<User>> result = new Result<>();
        //如果成功，设置状态码和查询到的结果
        result.setCode(HttpStatus.OK.value());
        result.setMsg("查询成功！");
//        List<User> users = userService.userList();
//        result.setData(users);
        //返回结果
        return result;
    }

    @PostMapping("/register")
    public Result<String> register(User user) throws Exception {
        //初始化返回值
        Result<String> result = new Result<>();
        //验证用户是否存在于数据库
        int flag = userService.check_user(user);

        if (flag == 1) {
            result.setCode(HttpStatus.NOT_FOUND.value());
            result.setMsg("用户已存在！");
        } else {
            if (userService.register(user) == 1) {
                result.setMsg("注册成功！");
            } else {
                result.setCode(HttpStatus.NOT_ACCEPTABLE.value());
                result.setMsg("注册失败！");
            }
        }

        return result;
    }

//    @PostMapping("updatePwd")
//    public Result<String> updatePwd(User user) throws Exception {
//        Result<String> result = new Result<>();
//        int flag = userService.updatePwd(user);
//
//        if (flag == 1) {
//            result.setCode(HttpStatus.OK.value());
//            result.setMsg("修改密码成功！");
//        }
//
//        return result;
//    }

}
