package com.lhc.goods.service;

import com.jfinal.plugin.activerecord.Db;
import com.lhc.common.model.Cart;

import java.util.List;

/**
 * @Description: 用户对cart表的查询、增添、删除
 * @Author: lhc
 * @Date: 2023-04-30 19:23
 **/
public class CartService {
    private final Cart cartDao = new Cart().dao();

    /**
     * @Description: 通过查询cart表获取goodID，从而得到在购物车中的物品信息
     * @param userId
     * @return cartList
     */
    public List<Cart> cartList(int userId) {
        String sql = cartDao.getSql("goodIdByCart");
        List<Cart> cartsList = cartDao.find("select * from goods where id = ?",cartDao.find(sql,userId));
        return cartsList;
    }

    /**
     * @Description: 将商品添加到购物车中
     * @param goodId，商品id
     * @param userId，购买者id
     */
    public void addCart(int goodId,int userId) {
        cartDao.set("goodId",goodId);
        cartDao.set("userId",userId);
        cartDao.save();
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
