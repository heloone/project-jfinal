package com.lhc.common.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * @Description: 拦截报错信息
 * @Author: lhc
 * @Date: 2023-05-26 17:07
 **/
public class ExceptionInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        try{
            inv.invoke();
        } catch (Exception e){
            e.printStackTrace();
           inv.getController().redirect("/common/500.html");
        }
    }
}

