package com.lhc.admin.service;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.lhc.common.model.Shopping;

import java.util.List;


/**
 * @Description: 关于购物订单的查询和搜索，订单不可被修改和删除
 * @Author: lhc
 * @Date: 2023-05-21 11:40
 **/
public class ShoppingService {

    private final Shopping ShoppingDao = new Shopping().dao();

    /**
     * 用limit进行分页，查询交易记录
     * @param page
     * @param limit
     * @param ownerId
     * @return
     */
    public List<Record> shoppingList(int page, int limit, int ownerId) {
        int pre = (page - 1) * limit;
        int last = page * limit;
        System.out.println(pre);
        System.out.println(last);
        //使用Db，若是使用ShoppingDAO会使user无法json化（应该可以用FastJson配置解决（未尝试））结果使用Db，时间无法序列化，还是要用fastjson
        //后续解决，不使用FastJson
        List<Record> shoppingList = Db.find(Db.getSql("sear_shopping-user"),ownerId,pre,last);
        return shoppingList;
    }

    /**
     * 根据findType进行模糊查询
     * @param page
     * @param limit
     * @param ownerId
     * @param findType 查询类别
     * @param findName 查询内容
     * @return
     */
    public List<Record> findList(int page, int limit, int ownerId, String findType, String findName) {
        Kv kv = Kv.create();
        List<Record> list = null;
        int pre = (page - 1) * limit;
        int last = page * limit;
        kv.set("ownerId",ownerId);
        kv.set("findName",findName);
        kv.set("page",pre);
        kv.set("limit",last);
        System.out.println(findType);

        if(findType.equals("用户名称")) list = Db.find(Db.getSqlPara("sear_shopping-userByUser",kv));
        if(findType.equals("用户账号")) list = Db.find(Db.getSqlPara("sear_shopping-userByUserName",kv));
        if(findType.equals("商品名称")) list = Db.find(Db.getSqlPara("sear_shopping-userByShoppingName",kv));
        if(findType.equals("商品类型")) list = Db.find(Db.getSqlPara("sear_shopping-userByType",kv));

        System.out.println(list);
        return list;
    }

    public List<Record> show_orders(int ownerId) {
        List<Record> show_orders = Db.find(Db.getSql("show_orders"),ownerId);
        return show_orders;
    }

}
