package com.lhc.goods.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lhc.common.model.Cart;

import java.util.List;

/**
 * @Description: 用户对cart表的查询、增添、删除
 * @Author: lhc
 * @Date: 2023-04-30 19:23
 **/
public class CartService {
    private final Cart cartDao = new Cart().dao();
    private final Cart cart = new Cart();

    /**
     * @Description: 通过查询cart表获取goodID，从而得到在购物车中的物品信息
     * @param userId
     * @return cartList
     */
    public List<Record> cartList(int userId) {
        String sql = Db.getSql("goodIdByCart");
        List<Record> cartsList = Db.find(sql,userId);
        return cartsList;
    }

    /**
     * @Description: 将商品添加到购物车中
     * @param goodId，商品id
     * @param userId，购买者id
     */
    public String addCart(int goodId,int userId) {
        List<Cart> carts = cartDao.find("select * from cart where goodId=? and userId=?",goodId,userId);
        if(carts.size() > 0){
            return "购物车中已存在，请勿重复添加";
        }else{
            cart.set("goodId",goodId);
            cart.set("userId",userId);
            cart.save();
            return "添加成功";
        }
    }

    /**
     * @Description: 将商品从购物车中删除
     * @param goodId，商品id
     * @param userId，购买者id
     */
    public void delectCart(int goodId,int userId) {
        String sql = cartDao.getSql("delectCart");
        Db.delete(sql,goodId,userId);
    }
}
