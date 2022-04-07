package com.example.backend.service;

import com.example.backend.mapper.WishlistMapper;
import com.example.backend.pojo.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WishlistService {
    @Autowired
    public WishlistMapper wishlistMapper;

    public ArrayList<Wishlist> getWishList(int user_id) {
        return wishlistMapper.getWishList(user_id);
    }

    public int setRead(int wishlist_id) {
        return wishlistMapper.setRead(wishlist_id);
    }

    public int delete(int wishlist_id) {
        return wishlistMapper.delete(wishlist_id);
    }
}
