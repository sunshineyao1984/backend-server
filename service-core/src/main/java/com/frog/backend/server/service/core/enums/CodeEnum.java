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
    ERROR_METOD_CALL(3,"错误的方法调用，请使用CodeEnum.DYNAMIC_CODE调用setDynamic(CodeEnum codeEnum, String msg)或setDynamicCode(Integer code, String msg)方法！"),
    UNAUTHORIZED(4, "暂未登录或token已经过期"),
    FORBIDDEN(5,"没有相关权限"),
    /**
     * >=1000 业务异常预留错误码
     */
    USER_NOT_LOGIN_ERROR(1000,"用户未登录"),
    /**
     * -1 动态码，用以动态错误信息显示
     */
    DYNAMIC_CODE(-1,"");
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

    /**
     * 自定义错误信息的方法,使用方法:
     * CodeEnum.DYNAMIC_CODE.setDynamic(CodeEnum codeEnum,String msg)
     * @param codeEnum
     * @param msg
     * @return
     */
    public CodeEnum setDynamic(CodeEnum codeEnum, String msg){
        checkCaller(this);
        this.code = codeEnum.getCode();
        this.msg = msg;
        return this;
    }

    /**
     * 自定义错误信息的方法,使用方法:
     * CodeEnum.DYNAMIC_CODE.setDynamicCode(Integer code,String msg)
     * @param code
     * @param msg
     * @return
     */
    public CodeEnum setDynamicCode(Integer code, String msg){
        checkCaller(this);
        this.code = code;
        this.msg = msg;
        return this;
    }

    /**
     * 检查调用者是否合法
     * @param codeEnum
     */
    private void checkCaller(CodeEnum codeEnum){
        if(codeEnum == null){
            throw new ServiceException(CodeEnum.ERROR_PARAM,"参数不能为null");
        }
        if(!CodeEnum.DYNAMIC_CODE.toString().equals(codeEnum.toString())){
            throw new ServiceException(CodeEnum.ERROR_METOD_CALL);
        }
    }
}
