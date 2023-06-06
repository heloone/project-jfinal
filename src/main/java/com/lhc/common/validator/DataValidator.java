package com.lhc.common.validator;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.validate.Validator;

/**
 * @Description: 验证添加和修改商品时数据非空
 * @Author: lhc
 * @Date: 2023-05-26 15:28
 **/
public class DataValidator extends Validator {

    protected void validate(Controller c) {
        this.setShortCircuit(true);
        validateRequiredString("name", "errorMsg", "请输入商品名称");
        validateRequiredString("type", "errorMsg", "请选择商品类型");
        validateRequiredString("info", "errorMsg", "请输入商品信息");
        validateRequiredString("price", "errorMsg", "请输入商品价格");
    }

    protected void handleError(Controller c) {
        String errorMsg = c.getAttr("errorMsg");
        System.out.println(errorMsg);
        c.keepPara("codeMsg");
        c.renderJson(Ret.msg(errorMsg));
    }
}
