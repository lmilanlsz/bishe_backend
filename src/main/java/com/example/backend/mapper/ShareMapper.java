package com.example.backend.mapper;

import com.example.backend.pojo.Share;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface ShareMapper {
    @Insert("insert into share(user_id, book_id, share_date) values (#{user_id}, #{book_id}, #{share_date}) ")
    int insertShare(Share share);

    @Select("select s.share_id, s.share_date, u.username from share s join user u where s.book_id = #{book_id} and s.user_id = u.user_id ")
    ArrayList<Share> getShareList(int book_id);

    @Delete("delete from share where share_id = #{share_id}")
    int deleteShare(int share_id);

    @Select("select book_id from share where share_id = #{share_id}")
    int getBook_id(int share_id);
}
