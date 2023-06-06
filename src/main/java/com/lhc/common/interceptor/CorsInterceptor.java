package com.lhc.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: response请求头设置，解决跨域问题
 * @Author: lhc
 * @Date: 2023-05-26 16:59
 **/
public class CorsInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        HttpServletRequest request = inv.getController().getRequest();
        HttpServletResponse response = inv.getController().getResponse();

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            inv.getController().renderNull();
            return;
        }
        inv.invoke();
    }
}