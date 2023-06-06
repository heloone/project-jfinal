package com.lhc.goods.service;

import com.jfinal.kit.Kv;
import com.lhc.common.MD5;
import com.lhc.common.model.Users;

import static com.lhc.common.JwtUtils.getToken;

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
    public void addUser(String name,String user,String phone,String address,String pass) {
        String password = MD5.getInstance().getMD5(pass);
        usersDao.setName(name);
        usersDao.setUser(user);
        usersDao.setPhone(phone);
        usersDao.setAddress(address);
        usersDao.setPassword(password);
        usersDao.save();
    }

    /**
     * @Description: 查询账号为name，密码为pass的用户
     * @param name，账号
     * @param pass，密码
     * @return 单个用户
     */
    public Users findUser(String name,String pass) {
        String password = MD5.getInstance().getMD5(pass);
        System.out.println("ok");
        Users user = usersDao.findFirst("select * from users where name = ? and password = ?",name,password);
        return user;
    }


    public Users getUser(int userId) {
        Users user = usersDao.dao().findById(userId);
        return user;
    }

    public String editUsers(int userId,String user,String last_pass,String address,String phone,String pre_pass) {
        System.out.println(last_pass);
        System.out.println(pre_pass);
        System.out.println(phone);
        String pre_password = MD5.getInstance().getMD5(pre_pass);
        String last_password = MD5.getInstance().getMD5(last_pass);
        Users users = usersDao.dao().findById(userId);
        if(users.getPassword().equals(pre_password)){
            //pass
            users.setUser(user);
            users.setAddress(address);
            users.setPhone(phone);
            users.setPassword(last_password);
            users.update();
            return "修改成功";
        }else
            return "密码错误";
    }

    public String pdUser(String name,String pass){
        String password = MD5.getInstance().getMD5(pass);
        Users user = usersDao.findFirst("select * from users where name = ?",name);
        if(user == null){
            return "该账号不存在";
        }else{
            user = usersDao.findFirst("select * from users where name = ? and password = ?",name,password);
            System.out.println(user);
            if(user == null){
                return "密码错误";
            }
        }
        return "登录成功";
    }


    public String get_token(Users user){
        Kv kv = Kv.create();
        kv.set("userId",user.getId().toString());
        kv.set("name",user.getName());
        kv.set("user",user.getUser());
        String token = getToken(kv);
        System.out.println(token);
        return token;
    }

    public String pdAddUser(String name){
        Users user = usersDao.findFirst("select * from users where name = ?",name);
        if(user != null){
            return "该账号已存在";
        }
        return "注册成功";
    }
}
