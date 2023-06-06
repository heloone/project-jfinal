package com.lhc.common.interceptor;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.lhc.common.JwtUtils;
import com.lhc.common.TokenInfo;


/**
 * @Description: 验证请求中携带的token
 * @Author: lhc
 * @Date: 2023-05-25 11:58
 **/
public class AdminTokenInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        System.out.println(controller.get("token"));
        String token = controller.get("token");
        if(token == null) token = controller.getHeader("authorization");
        if(token!=null){
            try {
                JwtUtils.verify(token);
            } catch (Exception e) {
                e.printStackTrace();
                controller.renderJson(Ret.msg("token invalid!"));
            }
            DecodedJWT info = JwtUtils.getTokenInfo(token);
            TokenInfo.setAdmin_token_infoToken_info(info);
            inv.invoke();
        }else{
            controller.renderText("token invalid!");
        }



    }
}
