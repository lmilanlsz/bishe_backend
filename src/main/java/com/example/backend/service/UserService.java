package com.example.backend.service;

import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User login(User user) {
        return userMapper.getUser(user.getUsername(), user.getUser_pwd());
    }

    public ArrayList<User> getUserIdById(int user_id) {
        return userMapper.getUserById(user_id);
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

    public ArrayList<User> getUserList() {
        return userMapper.getUserList();
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int deleteUser(int user_id) {
        return userMapper.deleteUser(user_id);
    }

    public ArrayList<String> getUsernameList() {
        return userMapper.getUserNameList();
    }
}
