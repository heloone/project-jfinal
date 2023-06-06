package com.lhc.goods.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lhc.common.model.Goods;
import com.lhc.common.model.Shopping;
import com.lhc.admin.service.GoodService;

import java.util.Date;

/**
 * @Description: 客户订单
 * @Author: lhc
 * @Date: 2023-05-23 21:20
 **/
public class ShoppingService {
    private final Shopping shopping = new Shopping();
    private final GoodService goodsService = new GoodService();
    private final CartService cartService = new CartService();

    public void add_shopping(int goodId,int userId){
        Goods good = new Goods().dao().findById(goodId);
        shopping.setUserId(userId);
        shopping.setOwnerId(good.getOwnerId());
        shopping.setImg(good.getImg());
        shopping.setInfo(good.getInfo());
        shopping.setName(good.getName());
        shopping.setPrice(good.getPrice());
        shopping.setType(good.getType());
        shopping.setTime(new Date());
        shopping.save();
    }

    public void addShopping(int goodId,int userId) {

        Db.tx(()->{
            add_shopping(goodId,userId);
            goodsService.delGood(goodId);
            System.out.println("del");
            cartService.delectCart(goodId,userId);
            return true;
        });
    }

    public Page<Record> getShopping(int page, int limit, int userId, String name) {
        Page<Record> shoppingPage = null;
        if(name == null)
         shoppingPage = Db.paginate(page,limit,"select admins.name as adminName,user,orders.name as name,img,info,type,price,time",
                "from admins inner join " + "(select * from shopping where userId = " + userId + ") as orders on orders.ownerId = admins.id");
        else
            shoppingPage = Db.paginate(page,limit,"select admins.name as adminName,user,orders.name as name,img,info,type,price,time",
                    "from admins inner join " + "(select * from shopping where userId = " + userId + ") as orders on orders.ownerId = admins.id" +
                            " where orders.name like '%"+name+"%'");
        return shoppingPage;
    }

}
