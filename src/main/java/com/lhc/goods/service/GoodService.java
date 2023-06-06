package com.lhc.goods.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.lhc.common.model.Goods;
import java.util.List;

/**
 * @Description: 关于用户对商品表的操作
 * @Author: lhc
 * @Date: 2023-04-30 18:23
 **/
public class GoodService {
    private final Goods goodsDao = new Goods().dao();

    /**
     * @Description: 查询所有的商品，采用CacheKit缓存
     * @return goodsList
     */
    public List<Goods> goodList() {
        List<Goods> goodsList = CacheKit.get("cacheName","good");
        if(goodsList == null){
            goodsList = goodsDao.find("select * from goods");
            CacheKit.put("cacheName","good",goodsList);
            System.out.println("没有缓存");
        }
//        List<Goods> goodsList = goodsDao.find("select * from goods");
        return goodsList;
    }

    public Goods good(int id) {
        Goods good = goodsDao.findFirst(Db.getSql("find_goods_admin"),id);
        return good;
    }


    /**
     * @Description: 查询名称为name的商品，通过price排序
     * @params: name：商品名称
     * @params: order:排序方式，1为从小到大，2为从大到小,空为按数据库顺序
     * @return: goodsList
     */
    public List<Goods> sortByname(String name,String order) {
        if(order == null) return goodsDao.find("select * from goods where name like '%"+"?"+"%'",name);
        else if(order.equals("1")) {
            String sql = goodsDao.getSql("sortBylittle");
            return goodsDao.find(sql,name);
        }else if(order.equals("2")) {
            String sql = goodsDao.getSql("sortBybig");
            return goodsDao.find(sql,name);
        }else return null;
    }

    public Page<Goods> sear_goods(int page,int limit,String name,String type) {
        Page<Goods> goodsPage = null;
        if(name == null){
            if(type == null)
                goodsPage = goodsDao.paginate(page,limit,"select *","from goods");
            else
                goodsPage = goodsDao.paginate(page,limit,"select *","from goods where type like '%"+ type +"%'");
        }else{
            if(type == null)
                goodsPage = goodsDao.paginate(page,limit,"select *","from goods where name like '%"+ name +"%'");
            else
                goodsPage = goodsDao.paginate(page,limit,"select *","from goods where type like '%"+ type +"%' and name like '%"+ name +"%'");
        }

        return goodsPage;
    }

    public List<Record> show_goods() {
        List<Record> list = Db.find(Db.getSql("show_goods"));
        return list;
    }



}
