package com.frog.backend.server.service.core.exception;

import com.frog.backend.server.service.core.enums.CodeEnum;

/**
 * Description 服务异常
 *
 * @author yxy
 * @date 2020-10-08
 */
public class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer errorCode;

    public ServiceException(Integer errorCode,String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public ServiceException(CodeEnum codeEnum){
        super(codeEnum.getMsg());
        this.errorCode = codeEnum.getCode();
    }

    public ServiceException(CodeEnum codeEnum,String errorMsg){
        super(errorMsg);
        this.errorCode = codeEnum.getCode();
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
