package com.lhc.common;

import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @Description: 获取验证成功后的token，从中解析数据返回
 * @Author: lhc
 * @Date: 2023-05-25 19:32
 **/
public class TokenInfo {
    private static DecodedJWT user_token_info = null;
    private static DecodedJWT admin_token_info = null;
    private static String cs = "123";

    public static void setAdmin_token_infoToken_info(DecodedJWT info) {
        admin_token_info = info;
    }

    public static void setUser_token_info(DecodedJWT info) {
        user_token_info = info;
    }

    public static int getAdminId() {
        if (admin_token_info == null) return 0;
        String adminId = admin_token_info.getClaims().get("adminId").asString();
        System.out.println("id："+ adminId);
        return Integer.parseInt(adminId);
    }

    public static int getUserId() {
        if (user_token_info == null) return 0;
        String userId = user_token_info.getClaims().get("userId").asString();
        System.out.println("id："+ userId);
        return Integer.parseInt(userId);
    }
}
