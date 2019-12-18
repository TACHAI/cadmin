package com.chaoxing.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


/**
 * 统一异常处理
 * Create by tachai on 2019-12-09 16:47
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Getter
public class BadRequestException extends RuntimeException{

    private Integer status = BAD_REQUEST.value();

    public BadRequestException(String msg){
        super(msg);
    }

    public BadRequestException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}
