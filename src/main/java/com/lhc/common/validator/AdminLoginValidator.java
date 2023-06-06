package com.lhc.common.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.validate.Validator;
import com.lhc.admin.service.AdminService;

/**
 * @Description: 对店家登录进行验证
 * @Author: lhc
 * @Date: 2023-05-25 10:06
 **/
public class AdminLoginValidator extends Validator {

    private final AdminService adminService = new AdminService();

    protected void validate(Controller c) {
        this.setShortCircuit(true);
        String username = c.get("username");
        String password = c.get("password");
        System.out.println(username);
        System.out.println(password);
        validateRequiredString("username", "errorMsg", "请输入用户名");
        validateRequiredString("password", "errorMsg", "请输入密码");
        validateCaptcha("code","errorMsg","验证码错误");

        String msg = adminService.pdAdmin(username,password);
        System.out.println(msg);
        if(msg.equals("该账号不存在")){
            addError("errorMsg", "该账号不存在");
        }else if(msg.equals("密码错误")) addError("errorMsg", "密码错误");
    }

    protected void handleError(Controller c) {
        String errorMsg = c.getAttr("errorMsg");
        System.out.println(errorMsg);
        c.keepPara("codeMsg");
        c.renderJson(Ret.msg(errorMsg));
    }
}
