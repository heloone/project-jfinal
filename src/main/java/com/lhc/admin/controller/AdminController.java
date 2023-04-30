package com.lhc.admin.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

@Path(value = "/admin", viewPath = "/admin")
public class AdminController extends Controller {
    public void login(){
        render("login.html");
    }
}
