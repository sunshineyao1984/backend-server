package com.frog.backend.server.service.core.enums;

import com.frog.backend.server.service.core.exception.ServiceException;

/**
 * Description 响应码枚举
 *
 * @author yxy
 * @date 2020-10-08
 */
public enum CodeEnum {

    /**
     * 0 成功
     */
    SUCCESS(0,"操作成功"),
    /**
     * 1-999 系统异常预留错误码
     */
    ERROR(1,"系统开小差了，请稍后再试"),
    ERROR_PARAM(2,"参数异常"),
    UNAUTHORIZED(4, "暂未登录或token已经过期"),
    FORBIDDEN(5,"没有相关权限"),
    /**
     * >=1000 业务异常预留错误码
     */
    USER_NOT_LOGIN_ERROR(1000,"用户未登录"),
    ;
    /**
     * 响应码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
