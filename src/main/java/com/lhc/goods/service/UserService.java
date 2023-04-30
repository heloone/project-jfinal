package com.lhc.goods.service;

import com.jfinal.plugin.activerecord.Db;
import com.lhc.common.MD5;
import com.lhc.common.model.Users;

/**
 * @Description: 用户登录添加用户、获取用户信息、修改个人信息、登录获取token
 * @Author: lhc
 * @Date: 2023-04-30 19:47
 **/
public class UserService {
    private final Users usersDao = new Users();
    //不能直接new Users().dao()

    /**
     * @Description: 添加用户，其中user用户昵称为空
     * @param name 账号
     * @param address 收货地址
     * @param pass 密码，通过MD5加密，防止数据库被盗
     */
    public void addUser(String name,String address,String pass) {
        usersDao.setName(name);
        usersDao.setAddress(address);
        usersDao.setPassword(MD5.getInstance().getMD5(pass));
        usersDao.save();
    }

    /**
     * @Description: 查询账号为name，密码为pass的用户
     * @param name，账号
     * @param pass，密码
     * @return 单个用户
     */
    public Users findUser(String name,String pass) {
        String sql = Db.getSql("findUser");
        Users user = usersDao.findFirst(sql,name,MD5.getInstance().getMD5(pass));
        return user;
    }

    /**
     *
     * @param name，账号
     * @param user，昵称
     * @param pass，密码
     * @param address，收货地址
     */
    public void upsetUser(String name,String user,String pass,String address) {
        usersDao.setName(name);
        usersDao.setUser(user);
        usersDao.setPassword(MD5.getInstance().getMD5(pass));
        usersDao.setAddress(address);
        usersDao.update();
    }
}
