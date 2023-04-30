package com.lhc;

import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.jfinal.template.ext.directive.NowDirective;
import com.lhc.common.model._MappingKit;
import com.lhc.common.route.AdminRoutes;
import com.lhc.common.route.UserRoutes;


/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: https://jfinal.com/club
 * 
 * API 引导式配置
 */
public class DemoConfig extends JFinalConfig {

	/**
	 * 启动入口，运行此 main 方法可以启动项目，此 main 方法可以放置在任意的 Class 类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		UndertowServer.start(DemoConfig.class,80,true);
	}
	
	/**
	 * PropKit.useFirstFound(...) 使用参数中从左到右最先被找到的配置文件
	 * 从左到右依次去找配置，找到则立即加载并立即返回，后续配置将被忽略
	 */

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 配置对超类中的属性进行注入
		me.setDevMode(true);
		//配置下载路径
		me.setBaseDownloadPath("demo/Download");
		//配置上传路径
		me.setBaseUploadPath("demo/Upload");
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		// 使用 jfinal 4.9.03 新增的路由扫描功能
		me.scan("com.lhc.");
		me.add(new UserRoutes());//前台路由
		me.add(new AdminRoutes());//后台路由
	}
	
	public void configEngine(Engine me) {
//		me.addSharedFunction("/common/_layout.html");
//		me.addSharedFunction("/common/_paginate.html");
		me.addDirective("now", NowDirective.class);
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		//配置数据库连接池
		DruidPlugin dp = new DruidPlugin("jdbc:mysql://localhost/test?characterEncoding=utf8&useSSL=false&zeroDateTimeBehavior=convertToNull","root","");
		me.add(dp);
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
		_MappingKit.mapping(arp);
		me.add(arp);
		//配置缓存机制
		me.add(new EhCachePlugin());
		//配置enjoy模板SQL
		arp.addSqlTemplate("all.sql");
		me.add(arp);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
//		me.add(new DemoInterceptor());
//		me.add(new LoginValidator());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}

}
