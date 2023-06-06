package com.lhc.common;

import com.jfinal.template.Directive;

import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;


import java.util.Date;

public class NowDirective extends Directive {
    public void exec(Env env, Scope scope, Writer writer){
        write(writer, new Date().toString());
    }

}
