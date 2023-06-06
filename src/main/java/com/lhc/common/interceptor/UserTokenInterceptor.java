package com.lhc.common.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.lhc.common.JwtUtils;
import com.lhc.common.TokenInfo;

/**
 * @Description: 对用户返回的token进行解析
 * @Author: lhc
 * @Date: 2023-05-25 20:56
 **/
public class UserTokenInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        System.out.println(controller.get("token"));
        String token = controller.get("token");
        if (token == null) token = controller.getHeader("authorization");
        if (token != null) {
            try {
                JwtUtils.verify(token);
            } catch (Exception e) {
                e.printStackTrace();
                controller.renderJson(Ret.msg("token invalid!"));
            }
            DecodedJWT info = JwtUtils.getTokenInfo(token);
            TokenInfo.setUser_token_info(info);
            inv.invoke();
        } else {
            controller.renderText("token invalid!");
        }
    }
}
