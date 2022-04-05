package com.example.backend.service;

import com.example.backend.mapper.WishlistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    @Autowired
    public WishlistMapper wishlistMapper;
}
