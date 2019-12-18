package com.chaoxing.exception;

import org.springframework.util.StringUtils;

/**
 * Create by tachai on 2019-12-09 17:01
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Class clazz, String field, String val) {
        super(EntityNotFoundException.generateMessage(clazz.getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }

}
