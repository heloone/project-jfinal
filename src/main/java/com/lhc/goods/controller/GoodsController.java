package com.lhc.goods.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.lhc.goods.service.*;

@Path(value = "/goods", viewPath = "/goods")
public class GoodsController extends Controller {
    private final GoodsService goodsService = new GoodsService();
    private final UserService userService = new UserService();
    public void login(){
        render("login.html");
    }
    public void index() {
        set("list",goodsService.goodList());
        render("index.html");
    }
    public void addUser() {
        userService.addUser("123","123","123");
        renderText("成功");
    }
}
