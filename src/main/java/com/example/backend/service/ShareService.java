package com.example.backend.service;

import com.example.backend.mapper.ShareMapper;
import com.example.backend.pojo.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ShareService {
    @Autowired
    ShareMapper shareMapper;

    public int insertShare(Share share) {
        return shareMapper.insertShare(share);
    }

    public ArrayList<Share> getShareList(int book_id) {
        return shareMapper.getShareList(book_id);
    }

    public int deleteShare(int share_id) {
        return shareMapper.deleteShare(share_id);
    }

    public int getBook_id(int share_id) {
        return shareMapper.getBook_id(share_id);
    }
}
