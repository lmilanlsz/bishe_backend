package com.example.backend.mapper;

import com.example.backend.pojo.Wishlist;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;

@Mapper
@Repository
public interface WishlistMapper {
    @Select("select w.wish_status, w.user_id, w.book_id, w.wishlist_id, w.wish_date, b.book_author, b.book_title, u.username from wishlist w " +
        "join user u on u.user_id = w.user_id join book b on b.book_id = w.book_id where w.user_id = #{user_id}  ")
    ArrayList<Wishlist> getWishList(int user_id);

    @Update("update wishlist set wish_status = 1 where wishlist_id = #{wishlist_id}")
    int setRead(int wishlist_id);

    @Delete("delete from wishlist where wishlist_id = #{wishlist_id}")
    int delete(int wishlist_id);

    @Insert("insert into wishlist (book_id, user_id, wish_date) values (#{book_id}, #{user_id}, #{date})")
    int uploadWishlist(int book_id, int user_id, Date date);

    @Select("select count(*) from wishlist where book_id = #{book_id}")
    int checkWishlist(int book_id);
}
