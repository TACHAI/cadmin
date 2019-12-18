package com.chaoxing.utils;

import com.chaoxing.exception.BadRequestException;

import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常工具
 * Create by tachai on 2019-12-09 17:11
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    public static void throwForeignKeyException(Throwable e, String msg){
        Throwable t = e.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
        }
        if (t != null) {
            throw new BadRequestException(msg);
        }
        assert false;
        throw new BadRequestException("删除失败：" + t.getMessage());
    }
}
