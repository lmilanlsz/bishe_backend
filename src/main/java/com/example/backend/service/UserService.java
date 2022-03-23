package com.example.backend.service;

import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User login(User user) {
        return userMapper.getUser(user.getUsername(), user.getUser_pwd());
    }

    public String getUserIdByName(String username) {
        return userMapper.getUserIdByName(username);
    }

    public int check_user(User user) {
        return userMapper.checkUser(user.getUsername());
    }

    public int register(User user) {
        return userMapper.registerUser(user.getUsername(), user.getUser_pwd());
    }
}
