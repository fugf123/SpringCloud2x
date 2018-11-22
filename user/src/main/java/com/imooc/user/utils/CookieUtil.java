package com.imooc.user.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static void setCookie(HttpServletResponse response,String name,
                                 String value,int maxAge){

        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static Cookie[] getCookie(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(null != cookies&&cookies.length>0){
            for(Cookie cookie:cookies){
                if (name.equals(cookie.getName())){
                    return cookies;
                }
            }
        }
        return null;
    }
}
