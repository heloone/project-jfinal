package com.lhc.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @Description: JWT工具类，含生成解析与获取token里的数据
 * @Author: lhc
 * @Date: 2023-05-09 10:09
 **/
public class JwtUtils {

    private static final String SING = "skyNHB";
    /**
     * 生成token
     */
    public static String getToken(Map<String,String> map) {
        //设置过期时间
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        //建立 jwt builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });

        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));

        return token;
    }

    /**
     * 验证token
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /**
     * 获取token信息
     */
    public static DecodedJWT getTokenInfo(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return verify;
    }
}
