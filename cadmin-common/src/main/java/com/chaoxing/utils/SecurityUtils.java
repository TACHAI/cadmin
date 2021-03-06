package com.chaoxing.utils;

import cn.hutool.json.JSONObject;
import com.chaoxing.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 获取当前登录的用户
 * Create by tachai on 2019-12-10 08:59
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class SecurityUtils {

    public static UserDetails getUserDetails() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BadRequestException(HttpStatus.UNAUTHORIZED, "登录状态过期");
        }
        return userDetails;
    }

    /**
     * 获取系统用户名称
     * @return 系统用户名称
     */
    public static String getUsername(){
        Object obj = getUserDetails();
        return new JSONObject(obj).get("username", String.class);
    }

    /**
     * 获取系统用户id
     * @return 系统用户id
     */
    public static Long getUserId(){
        Object obj = getUserDetails();
        return new JSONObject(obj).get("id", Long.class);
    }
}
