package com.lhc;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.render.Render;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//@Path(value = "/admin", viewPath = "/admin")
//@Path("/goods/html")
public class Demo extends Controller{
    public void login(){
        render("login.html");
    }
    public void index(){
        render("index.html");
    }

}


