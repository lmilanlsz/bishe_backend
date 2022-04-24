package com.example.backend.controller;


import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class BaseController {
    /**
     * 注入全部service
     */
    @Autowired
    public UserService userService;

    @Autowired
    public BookService bookService;

    @Autowired
    public ReviewService reviewService;

    @Autowired
    public WishlistService wishlistService;

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public ShareService shareService;

//    @Autowired
//    protected GoodsService goodsService;
//
//    @Autowired
//    protected RecordService recordService;
    /**
     * 创建session、Request、Response等对象
     */
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;


    /**
     * 在每个子类方法调用之前先调用
     * 设置request,response,session这三个对象
     *
     * @param request
     * @param response
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        //可以在此处拿到当前登录的用户
    }

}
