package com.lhc.admin.controller;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.kit.Kv;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import com.lhc.admin.service.AdminService;
import com.lhc.admin.service.GoodService;
import com.lhc.admin.service.ShoppingService;
import com.lhc.common.TokenInfo;
import com.lhc.common.interceptor.AdminTokenInterceptor;
import com.lhc.common.model.Admins;
import com.lhc.common.model.Goods;
import com.lhc.common.validator.AdminLoginValidator;
import com.lhc.common.validator.DataValidator;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path(value = "/admin")
public class AdminController extends Controller {
    private final GoodService goodService = new GoodService();
    private final ShoppingService shoppingService = new ShoppingService();
    private final AdminService adminService = new AdminService();
    private static String fileName;

    public void login(){
        render("login.html");
    }

    public void cs() {render("cs.html");}

    @Before(AdminTokenInterceptor.class)
    public void index() {
        int adminId = TokenInfo.getAdminId();
        Admins admins = adminService.getAdmin(adminId);
        System.out.println(admins);
        set("admins",admins);
        render("index.html");
    }

    //获取商品
    public void getGoods() {
        int adminId = TokenInfo.getAdminId();
        System.out.println("adminId"+adminId);
        Map<String,Object> result = new HashMap<>();
//        int owner = Integer.parseInt(get("id"));
        int page = Integer.parseInt(get("page"));
        int limit = Integer.parseInt(get("limit"));
        Page<Goods> goodPage = goodService.goodsPage(page,limit,adminId);

        result.put("data",goodPage);
        result.put("code",0);
        result.put("msg","成功");
        result.put("count",goodPage.getTotalRow());
//        renderJson(Ret.ok().set(result).toJson());
        renderJson(JSON.toJSON(result));
    }

    //修改商品
    @Before(DataValidator.class)
    public void editGood() {
        int id = Integer.parseInt(get("id"));
        String name = get("name");
        String img = null;
        if (fileName == null)
            img = get("img");
        else
            img = fileName;

        String type = get("type");
        String info = get("info");
        String price =get("price");
        System.out.println(name);
        goodService.editGood(id,name,img,type,info,price);
        fileName = null;
        renderJson(Ret.msg("修改成功"));
    }

    //上传图片
    public void upLoad() {
        UploadFile file = getFile();
        fileName = "../upload/" + file.getFileName();
        System.out.println(file.getUploadPath());
        System.out.println(file.getFileName());
        Map<String,Object> data = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        data.put("src",fileName);
        result.put("data",data);
        result.put("code",0);
        result.put("msg","成功");
        renderJson(Ret.ok().set(result).toJson());
    }

    //添加商品
    @Before(DataValidator.class)
    public void addGood() {
        int adminId = TokenInfo.getAdminId();
        String name = get("name");
//        String img = fileName;
        String type = get("type");
        String info = get("info");
        String price =get("price");
        goodService.addGood(name,fileName,type,info,price,adminId);
        renderJson(Ret.msg("添加成功"));
    }

    //查询商品名称
    public void findGoods() {
        String name = get("name");
        int page = Integer.parseInt(get("page"));
        int limit = Integer.parseInt(get("limit"));
        List<Goods> list = goodService.searchGoods(page,limit,name);
        System.out.println(list);

        Kv kv = Kv.create();
        kv.set("data",list);
        kv.set("code",0);
        kv.set("msg","成功");
        kv.set("count",goodService.sear_count(name));
        renderJson(Ret.ok().set(kv).toJson());
    }

    //删除商品
    public void delGood() {
        int id = Integer.parseInt(get("id"));
        goodService.delGood(id);
        renderJson(Ret.msg("删除成功"));
    }

    //获取订单
    public void getShopping() {
        int adminId = TokenInfo.getAdminId();
        int page = Integer.parseInt(get("page"));
        int limit = Integer.parseInt(get("limit"));
        List<Record> shoppingList = shoppingService.shoppingList(page,limit,adminId);

        Kv kv = Kv.create();
        kv.set("data",shoppingList);
        kv.set("code",0);
        kv.set("msg","成功");
        kv.set("count",8);

        System.out.println(kv);
        renderJson(Ret.ok().set(kv));
    }

    //查询订单交易名称
    public void findShopping() {
        int adminId = TokenInfo.getAdminId();
        Kv kv = Kv.create();
        int page = Integer.parseInt(get("page"));
        int limit = Integer.parseInt(get("limit"));
        String findType = get("type");
        String findName = get("name");
        List<Record> findList = shoppingService.findList(page,limit,adminId,findType,findName);
        kv.set("data",findList);
        kv.set("code",0);
        kv.set("msg","成功");
        kv.set("count",findList.size());
        renderJson(kv);
    }

    //获取个人信息
    public void getInfo() {
        int adminId = TokenInfo.getAdminId();
        Admins admins = adminService.getAdmin(adminId);
        System.out.println(get("user"));
        set("admins",admins);
        render("adminInfo.html");
    }

    //修改个人信息
    public void editInfo() {
        int adminId = TokenInfo.getAdminId();
        System.out.println("jia");
        String user = get("user");
        String pre_pass = get("pre_pass");
        String last_pass = get("last_pass");
        String address = get("address");
        String phone = get("phone");

        String msg = adminService.editAdmin(adminId,user,last_pass,address,phone,pre_pass);
        System.out.println(msg);
        renderJson(Ret.msg(msg));
    }

    //可视化，订单种类
    public void show_orders(){
        int adminId = TokenInfo.getAdminId();
        List<Record> list = shoppingService.show_orders(adminId);
        System.out.println(list);
        set("list",list);
        render("../common/echart.html");
    }

    //验证码
    public void get_code() {
        renderCaptcha();
    }

    //登录验证
    @Before(AdminLoginValidator.class)
    public void findAdmin() {
        String name = get("username");
        String pass = get("password");
        System.out.println(name);
        Admins admin = adminService.findAdmin(name,pass);
        String token = adminService.get_token(admin);
        System.out.println(admin);
        renderJson(Ret.ok().set("token",token));
    }


}
