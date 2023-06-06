package com.lhc.common.route;

import com.jfinal.config.Routes;

/**
 * @Description: 前台路由
 * @Author: lhc
 * @Date: 2023-04-30 17:24
 **/
public class UserRoutes extends Routes {
    public void config(){
        this.scan("com.lhc.goods");
        setBaseViewPath("/goods");
    }
}
