package com.lhc.common.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.validate.Validator;
import com.lhc.goods.service.UserService;

/**
 * @Description: 对用户注册进行验证
 * @Author: lhc
 * @Date: 2023-05-25 21:11
 **/
public class UserRegistValidator extends Validator {
    private final UserService userService = new UserService();

    protected void validate(Controller c) {
        this.setShortCircuit(true);
        String username = c.get("username");
        String password = c.get("password");
        System.out.println(username);
        System.out.println(password);
        validateRequiredString("username", "errorMsg", "请输入账号");
        validateRequiredString("password", "errorMsg", "请输入密码");
        validateRequiredString("user", "errorMsg", "请输入用户名");
        validateRequiredString("phone","errorMsg", "请输入联系电话");
        validateRequiredString("address", "errorMsg", "请输入收货地址");
        validateString("phone",11,11,"errorMsg","请输入正确的电话号码");

        String msg = userService.pdAddUser(username);
        System.out.println(msg);
        if(msg.equals("该账号已存在")) addError("errorMsg", "该账号已存在，请重新注册");
    }

    protected void handleError(Controller c) {
        String errorMsg = c.getAttr("errorMsg");
        System.out.println(errorMsg);
        c.renderJson(Ret.msg(errorMsg));
    }
}
