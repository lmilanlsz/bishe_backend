package com.example.backend.mapper;

import com.example.backend.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where username = #{username} and user_pwd = #{user_pwd}")
    User getUser(String username, String user_pwd);

    @Select("select user_id from user where username = #{username} ")
    String getUserIdByName(String username);

    @Select("select count(*) from user where username = #{username}")
    int checkUser(String user);

    @Insert("insert into user (username, user_pwd) values (#{username}, #{user_pwd})")
    int registerUser(String username,String user_pwd);

    @Select("select * from user")
    @Results({
            @Result(column = "user_id", property = "user_id"),
            @Result(column = "username", property = "username"),
            @Result(column = "user_pwd", property = "user_pwd")
    })
    ArrayList<User> getUserList();

}
