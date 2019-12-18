package com.chaoxing.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Create by tachai on 2019-12-09 17:03
 * gitHub https://github.com/TACHAI
 * Email tc1206966083@gmail.com
 */
@Data
public class ApiError {

    private Integer status = 400;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public static ApiError error(String message){
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    public static ApiError error(Integer status, String message){
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        return apiError;
    }
}
