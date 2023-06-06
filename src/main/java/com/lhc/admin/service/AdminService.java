package com.lhc.admin.service;


import com.jfinal.kit.Kv;

import com.lhc.common.MD5;
import com.lhc.common.model.Admins;

import static com.lhc.common.JwtUtils.*;

/**
 * @Description: 查询与更新管理员的个人信息
 * @Author: lhc
 * @Date: 2023-05-22 10:53
 **/
public class AdminService {

    private final Admins AdminDao = new Admins().dao();
    private final Admins Admin = new Admins();

    public Admins getAdmin(int adminId) {
        Admins admins = AdminDao.findById(adminId);
        return admins;
    }

    public String editAdmin(int adminId,String user,String last_pass,String address,String phone,String pre_pass) {
        System.out.println(last_pass);
        System.out.println(pre_pass);
        System.out.println(phone);
        String pre_password = MD5.getInstance().getMD5(pre_pass);
        String last_password = MD5.getInstance().getMD5(last_pass);
        Admins admin = AdminDao.findById(adminId);
        if(admin.getPassword().equals(pre_password)){
            //pass
            admin.setUser(user);
            admin.setAddress(address);
            admin.setPhone(phone);
            admin.setPassword(last_password);
            admin.update();
            return "修改成功";
        }else
            return "密码错误";

    }

    public Admins findAdmin(String name,String pass) {
        String password = MD5.getInstance().getMD5(pass);
        System.out.println("ok");
        Admins admin = AdminDao.findFirst("select * from admins where name = ? and password = ?",name,password);
        return admin;
    }

    public String get_token(Admins admin){
        Kv kv = Kv.create();
        kv.set("adminId",admin.getId().toString());
        kv.set("name",admin.getName());
        kv.set("user",admin.getUser());
        String token = getToken(kv);
        return token;
    }

    public String pdAdmin(String name,String pass){
        String password = MD5.getInstance().getMD5(pass);
        Admins admins = AdminDao.findFirst("select * from admins where name = ?",name);
        if(admins == null){
            return "该账号不存在";
        }else{
            admins = AdminDao.findFirst("select * from admins where name = ? and password = ?",name,password);
            System.out.println(admins);
            if(admins == null){
                return "密码错误";
            }
        }
        return "登录成功";
    }
}
