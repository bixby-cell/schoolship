package com.school.schoolship.exception;

import io.swagger.models.auth.In;

/**
 * @author Bixby
 * @create 2021-06-18
 * 统一异常
 */
public class MallException extends RuntimeException{
    private static Integer code;

    private static String message;

    public static Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public MallException(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public MallException(MallExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }
}
