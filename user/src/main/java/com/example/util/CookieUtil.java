package com.example.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xuan
 * @create 2018-06-08 12:48
 **/
public class CookieUtil {

    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue, int expireTime) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(expireTime);
        cookie.setPath("/");    //否则登录成功后，当请求路径变了，cookie携带不过去
        response.addCookie(cookie);
    }

    public static String get(HttpServletRequest request, String token) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (token.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
