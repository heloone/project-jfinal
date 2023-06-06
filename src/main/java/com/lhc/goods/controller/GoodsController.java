package com.lhc.goods.controller;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.alibaba.fastjson.JSON;
import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lhc.common.TokenInfo;
import com.lhc.common.interceptor.UserTokenInterceptor;
import com.lhc.common.model.Goods;
import com.lhc.common.model.Shopping;
import com.lhc.common.model.Users;
import com.lhc.common.validator.*;
import com.lhc.goods.service.*;

import java.util.List;

@Path(value = "/goods", viewPath = "/goods")
public class GoodsController extends Controller {
    private final GoodService goodsService = new GoodService();
    private final UserService userService = new UserService();
    private final CartService cartService = new CartService();
    private final ShoppingService shoppingService = new ShoppingService();

    public void login() {
        render("login.html");
    }

    @Before(UserTokenInterceptor.class)
    public void index() {
        int userId = TokenInfo.getUserId();
        Users user = userService.getUser(userId);
        System.out.println(get("user"));
        set("user",user);
        set("list",goodsService.goodList());
        render("layui.html");
    }

    public void getInfo() {
        int id = Integer.parseInt(get("id"));
        Goods good = goodsService.good(id);
        set("good",good);
        System.out.println(good);
        render("goodInfo.html");
    }

    public void sear_goods() {
        Kv kv = Kv.create();
        int page = Integer.parseInt(get("page"));
        int limit = Integer.parseInt(get("limit"));
        String type = get("type");
        String name = get("name");
        System.out.println(name);
        Page<Goods> goodsPage = goodsService.sear_goods(page,limit,name,type);
        kv.set("goodsPage",goodsPage);
        kv.set("list",goodsPage.getList());
        renderJson(kv);
    }

    public void addCart() {
        int userId = TokenInfo.getUserId();
        int goodId = Integer.parseInt(get("id"));
        String msg = cartService.addCart(goodId,userId);
        renderJson(Ret.msg(msg));
    }

    public void searCart() {
        int userId = TokenInfo.getUserId();
        Kv kv = Kv.create();
        List<Record> cartList = cartService.cartList(userId);
        System.out.println(cartList);
        kv.set("data",cartList);
        kv.set("code",0);
        kv.set("msg","成功");
        kv.set("count",cartList.size());
        renderJson(kv);
    }

    public void delCart() {
        int userId = TokenInfo.getUserId();
        int goodId = Integer.parseInt(get("goodId"));
        System.out.println(goodId);
        cartService.delectCart(goodId,userId);
        renderJson(Ret.msg("删除成功"));
    }

    public void addShopping() {
        int userId = TokenInfo.getUserId();
        int goodId = Integer.parseInt(get("goodId"));
        shoppingService.addShopping(goodId,userId);
        renderJson(Ret.msg("购买成功"));
    }

    public void getShopping() {
        int userId = TokenInfo.getUserId();
        Kv kv = Kv.create();
        int page = Integer.parseInt(get("page"));
        int limit = Integer.parseInt(get("limit"));
        String name = null;
        if(get("name").equals("undefined")) name = null;
        else name =get("name");
        System.out.println(name);
        Page<Record> shoppingPage = shoppingService.getShopping(page,limit,userId,name);
        kv.set("data",shoppingPage.getList());
        kv.set("code",0);
        kv.set("msg","成功");
        kv.set("count",shoppingPage.getTotalRow());
        renderJson(kv);
    }

    //获取个人信息
    public void getUser() {
        int userId = TokenInfo.getUserId();
        System.out.println("进来了");
        Users user = userService.getUser(userId);
        System.out.println(get("user"));
        set("admins",user);
        render("userInfo.html");
    }

    //获取个人信息
    public void getName() {
        int userId = TokenInfo.getUserId();
        Users user = userService.getUser(userId);
        System.out.println(user);
        renderJson(Ret.ok().set("user",user.getUser()));
    }

    //修改个人信息
    public void editInfo() {
        int userId = TokenInfo.getUserId();
        System.out.println("jia");
        String user = get("user");
        String pre_pass = get("pre_pass");
        String last_pass = get("last_pass");
        String address = get("address");
        String phone = get("phone");

        String msg = userService.editUsers(userId,user,last_pass,address,phone,pre_pass);
        System.out.println(msg);
        renderJson(Ret.msg(msg));
    }

    public void show_goods(){
        List<Record> list = goodsService.show_goods();
        set("list",list);
        render("../common/echart.html");
    }

    @Before(UserLoginValidator.class)
    public void findUser() {
        String name = get("username");
        String pass = get("password");
        System.out.println(name);
        Users user = userService.findUser(name,pass);
        String token = userService.get_token(user);
        System.out.println(user);
        renderJson(Ret.ok().set("token",token));
    }

    //添加用户
    @Before(UserRegistValidator.class)
    public void addUser() {
        String name = get("username");
        String pass = get("password");
        String user = get("user");
        String phone = get("phone");
        String address = get("address");
        System.out.println(name);
        userService.addUser(name,user,phone,address,pass);
        Users users = userService.findUser(name,pass);
        String token = userService.get_token(users);
        System.out.println(users);
        renderJson(Ret.ok().set("token",token));
    }


}
