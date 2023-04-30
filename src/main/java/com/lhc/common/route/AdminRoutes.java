package com.lhc.common.route;

import com.jfinal.config.Routes;

/**
 * @Description: 管理员路由
 * @Author: lhc
 * @Date: 2023-04-30 17:27
 **/
public class AdminRoutes extends Routes {
    @Override
    public void config() {
        setBaseViewPath("/admin");
    }
}
