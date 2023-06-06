package com.lhc.common.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.validate.Validator;

/**
 * @Description: 对修改个人信息进行非空验证
 * @Author: lhc
 * @Date: 2023-05-26 16:33
 **/
public class InfoValidator extends Validator {
    protected void validate(Controller c) {
        this.setShortCircuit(true);
        validateRequiredString("username", "errorMsg", "请输入账号");
        validateRequiredString("password", "errorMsg", "请输入密码");
        validateRequiredString("user", "errorMsg", "请输入用户名");
        validateRequiredString("phone","errorMsg", "请输入联系电话");
        validateRequiredString("address", "errorMsg", "请输入收货地址");
        validateString("phone",11,11,"errorMsg","请输入正确的电话号码");
    }

    protected void handleError(Controller c) {
        String errorMsg = c.getAttr("errorMsg");
        System.out.println(errorMsg);
        c.renderJson(Ret.msg(errorMsg));
    }
}
