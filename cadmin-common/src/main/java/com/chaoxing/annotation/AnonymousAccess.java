package com.chaoxing.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记匿名访问方法
 * Create by tachai on 2019-12-10 12:47
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {
}
