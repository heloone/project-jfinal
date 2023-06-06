package com.lhc.admin.service;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.ehcache.CacheKit;
import com.lhc.common.model.Goods;

import java.util.List;

/**
 * @Description: 商家对自己的商品进行增添查改
 * @Author: lhc
 * @Date: 2023-05-12 19:36
 **/
public class GoodService {

    private final Goods goodsDao = new Goods().dao();
    private final Goods good = new Goods(); //dao只能进行查询操作

    /**
     * 分页查询
     * @param page 第几页
     * @param limit 每页几个
     * @return Page<Goods>
     */
    public Page<Goods> goodsPage(int page,int limit,int ownerId) {
        Page<Goods> goodsPage = goodsDao.paginate(page,limit,"select *","from goods where ownerId = " + ownerId);
        return goodsPage;
    }

    /**
     * 事务操作，更新商品与购物车
     * @param id 商品id，用来确认具体物品
     * @param name 商品名称
     * @param img 商品图片
     * @param type 商品类型
     * @param info 商品信息
     * @param price 商品价格
     */
    public void editGood(int id, String name, String img, String type, String info, String price) {
        Db.tx(()->{
            Db.update(goodsDao.getSql("upsetGood"), name, img, type, info, price, id);
            System.out.println(img);
//            Db.update() 购物车也要更新
            return true;
        });
        CacheKit.remove("cacheName","good");
    }

    /**
     * 添加商品
     * @param name 商品名称
     * @param img 商品图片
     * @param type 商品类型
     * @param info 商品信息
     * @param price 商品价格
     * @param ownerId 商品所有人
     */
    public void addGood(String name, String img, String type, String info, String price, int ownerId) {
        good.setName(name);
        good.setImg(img);
        good.setType(type);
        good.setInfo(info);
        good.setPrice(price);
        good.setOwnerId(ownerId);
        good.save();
        CacheKit.remove("cacheName","good");
    }

    /**
     * 使用数据库的limit进行分页（limit，从第几个到第几个）
     * pre = (page -1)*limit 每页第一个
     * last = page*limit-1 每页最后一个
     * @param page 第几页
     * @param limit 每页限制几个
     * @param goodName 被搜索的商品名
     * @return List<Goods> 搜索得到的商品列表
     */
    public List<Goods> searchGoods(int page, int limit, String goodName) {
        Kv kv = Kv.create();
        int pre = (page - 1) * limit;
        int last = page * limit;
        kv.set("goodName",goodName);
        kv.set("page",pre);
        kv.set("limit",last);
        List<Goods> goodsList = goodsDao.find(goodsDao.getSqlPara("find_good",kv));
        return goodsList;
    }

    public List<Goods> sear_count(String goodName) {
        List<Goods> goodsList = goodsDao.find(goodsDao.getSql("good_count"),goodName);
        return goodsList;
    }

    /**
     * 根据商品id删除商品，购物车如果有也会被删除，并发出提示
     * @param id
     */
    public void delGood(int id) {
        Db.tx(()->{
            Db.delete(goodsDao.getSql("del_good"),id);
//            Db.delete("delete from goods where id = " + id);
            Db.delete("delete from cart where goodId =" + id); //购物车也要删除，并发出提示
            return true;
        });
        CacheKit.remove("cacheName","good");
    }

}
