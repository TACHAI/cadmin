package com.chaoxing.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 获取 HttpServletRequest
 * Create by tachai on 2019-12-10 08:54
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class RequestHolder {

    public static HttpServletRequest getHttpServletRequest(){
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
