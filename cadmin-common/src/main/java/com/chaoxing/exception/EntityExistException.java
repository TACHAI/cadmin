package com.chaoxing.exception;

import org.springframework.util.StringUtils;

/**
 * Create by tachai on 2019-12-09 16:58
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class EntityExistException extends RuntimeException{

    public EntityExistException(Class clazz,String field,String val){
        super(EntityExistException.generateMessage(clazz.getSimpleName(),field,val));
    }

    private static String generateMessage(String entity,String field,String val){
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }
}
