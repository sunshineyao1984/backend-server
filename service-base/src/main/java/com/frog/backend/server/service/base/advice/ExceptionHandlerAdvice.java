package com.frog.backend.server.service.base.advice;

import com.frog.backend.server.service.base.util.ResultUtils;
import com.frog.backend.server.service.core.enums.CodeEnum;
import com.frog.backend.server.service.core.exception.ServiceException;
import com.frog.backend.server.service.core.pojo.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Description 统一异常处理
 *
 * @author yxy
 * @date 2020-10-08
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResult handle(HttpServletRequest request, Throwable throwable){
        log.info("HttpServletRequest URI={}",request.getRequestURI());
        log.error("",throwable);

        //系统自定义异常处理
        if(throwable instanceof ServiceException){
            ServiceException serviceException = (ServiceException) throwable;
            ResultUtils.returnBaseResultDynamicCode(serviceException.getErrorCode(),serviceException.getMessage());
        }

        if(throwable instanceof MethodArgumentNotValidException){
            BindingResult bindingResult = ((MethodArgumentNotValidException) throwable).getBindingResult();
            String message = null;
            if (bindingResult.hasErrors()) {
                FieldError fieldError = bindingResult.getFieldError();
                if (fieldError != null) {
                    message = fieldError.getField()+fieldError.getDefaultMessage();
                }
            }
            return ResultUtils.returnBaseResultDynamic(CodeEnum.ERROR_PARAM,message);
        }

        if(throwable instanceof BindException){
            BindingResult bindingResult =((BindException) throwable).getBindingResult();
            String message = null;
            if (bindingResult.hasErrors()) {
                FieldError fieldError = bindingResult.getFieldError();
                if (fieldError != null) {
                    message = fieldError.getField()+fieldError.getDefaultMessage();
                }
            }
            return ResultUtils.returnBaseResultDynamic(CodeEnum.ERROR_PARAM,message);
        }

        return ResultUtils.returnBaseResultDynamic(CodeEnum.ERROR,throwable.getMessage());
    }
}
