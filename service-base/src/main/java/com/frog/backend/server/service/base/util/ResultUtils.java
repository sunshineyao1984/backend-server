package com.frog.backend.server.service.base.util;

import com.frog.backend.server.service.base.pojo.vo.data.PageData;
import com.frog.backend.server.service.core.enums.CodeEnum;
import com.frog.backend.server.service.core.pojo.vo.BaseResult;
import com.frog.backend.server.service.core.pojo.vo.Result;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * Description 返回数据工具类
 *
 * @author yxy
 * @date 2020-10-08
 */
public class ResultUtils {

    /**
     * 返回成功基本模型
     *
     * @return
     */
    public static BaseResult returnBaseResultSuccess() {
        return new BaseResult(CodeEnum.SUCCESS);
    }

    /**
     * 返回成功基本模型，自定义msg
     *
     * @param msg
     * @return
     */
    public static BaseResult returnBaseResultSuccess(String msg) {
        return returnBaseResultDynamic(CodeEnum.SUCCESS, msg);
    }

    /**
     * 是否成功基本模型
     *
     * @param baseResult
     * @return
     */
    public static Boolean isSuccessBaseResult(BaseResult baseResult) {
        if (baseResult != null && baseResult.getCode() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 返回失败基本模型
     *
     * @return
     */
    public static BaseResult returnBaseResultError() {
        return new BaseResult(CodeEnum.ERROR);
    }

    /**
     * 返回失败基本模型,指定错误枚举
     *
     * @param codeEnum
     * @return
     */
    public static BaseResult returnBaseResultError(CodeEnum codeEnum) {
        return returnBaseResultDynamic(codeEnum, null);
    }

    /**
     * 返回失败基本模型,指定错误枚举及错误msg
     *
     * @param codeEnum
     * @return
     */
    public static BaseResult returnBaseResultError(CodeEnum codeEnum, String msg) {
        return returnBaseResultDynamic(codeEnum, msg);
    }

    /**
     * 返回动态基本模型
     *
     * @param codeEnum
     * @param msg
     * @return
     */
    public static BaseResult returnBaseResultDynamic(CodeEnum codeEnum, String msg) {
        if (codeEnum == null) {
            return new BaseResult(CodeEnum.ERROR);
        }
        if (StringUtils.isBlank(msg)) {
            return new BaseResult(codeEnum);
        }
        return new BaseResult(codeEnum.getCode(), msg);
    }

    /**
     * 返回动态基本模型，自定义code
     *
     * @param code
     * @param msg
     * @return
     */
    public static BaseResult returnBaseResultDynamicCode(Integer code, String msg) {
        if (code == null) {
            return new BaseResult(CodeEnum.ERROR);
        }
        return new BaseResult(code, msg);
    }

    /**
     * 返回成功标准模型
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> returnResultSuccess(T data) {
        return new Result<>(CodeEnum.SUCCESS, data);
    }

    /**
     * 返回成功标准模型, 自定义msg
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> returnResultSuccess(String msg, T data) {
        return new Result<>(CodeEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 是否成功标准模型
     *
     * @param result
     * @param <T>
     * @return
     */
    public static <T> Boolean isSuccessResult(Result<T> result) {
        if (result != null && result.getCode() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 返回失败标准模型
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> returnResultError(T data) {
        return new Result<>(CodeEnum.ERROR, data);
    }

    /**
     * 返回失败标准模型,自定义枚举
     *
     * @param codeEnum
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> returnResultError(CodeEnum codeEnum, T data) {
        if (codeEnum == null) {
            return new Result<>(CodeEnum.ERROR, data);
        }
        return new Result<>(codeEnum, data);
    }

    /**
     * 返回失败标准模型,自定义msg
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> returnResultError(String msg, T data) {
        return new Result<>(CodeEnum.ERROR.getCode(), msg, data);
    }

    /**
     * 返回失败标准模型,自定义枚举和msg
     *
     * @param codeEnum
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> returnResultError(CodeEnum codeEnum, String msg, T data) {
        return returnResultDynamic(codeEnum, msg, data);
    }

    /**
     * 返回动态标准模型
     *
     * @param codeEnum
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> returnResultDynamic(CodeEnum codeEnum, String msg, T data) {
        if (codeEnum == null) {
            return new Result<>(CodeEnum.ERROR, data);
        }
        if (StringUtils.isBlank(msg)) {
            return new Result<>(codeEnum, data);
        }
        return new Result<>(codeEnum.getCode(), msg, data);
    }

    /**
     * 返回动态标准模型，code自定义
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> returnResultDynamicCode(Integer code, String msg, T data) {
        if (code == null) {
            return new Result<>(CodeEnum.ERROR, data);
        }
        return new Result<>(code, msg, data);
    }

    /**
     * 返回动态标准模型，由baseResult和data构成
     *
     * @param baseResult
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> returnResultFromBaseResult(BaseResult baseResult, T data) {
        return new Result<>(baseResult.getCode(), baseResult.getMsg(), data);
    }

    /**
     * 源标准模型复制到目标标准模型
     *
     * @param sourceResult
     * @param targetResult
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> Result<T> copyResult(Result<S> sourceResult, Result<T> targetResult) {
        if (sourceResult == null || targetResult == null) {
            return returnResultDynamic(CodeEnum.ERROR, "sourceResult或targetResult为null", null);
        }
        targetResult.setCode(sourceResult.getCode());
        targetResult.setMsg(sourceResult.getMsg());
        return targetResult;
    }

    /**
     * 返回成功标准模型，以分页数据对象为data
     *
     * @param pageInfo
     * @param <T>
     * @return
     */
    public static <T> Result<PageData<T>> returnResultFillPageInfo(PageInfo<T> pageInfo) {
        return returnResultFillPageInfo(pageInfo, null);
    }

    /**
     * 返回成功标准模型，以分页数据对象为data,并增加其它非列表数据
     *
     * @param pageInfo
     * @param otherData
     * @param <T>
     * @return
     */
    public static <T> Result<PageData<T>> returnResultFillPageInfo(PageInfo<T> pageInfo, Object otherData) {
        PageData<T> data = getPageData(pageInfo);
        data.setOtherData(otherData);
        return new Result<>(CodeEnum.SUCCESS, data);
    }

    /**
     * 私有方法，pagehelper的PageInfo对象转换为自己封装的PageData对象
     *
     * @param pageInfo
     * @param <T>
     * @return
     */
    private static <T> PageData<T> getPageData(PageInfo<T> pageInfo) {
        PageData<T> pageData = new PageData<>();
        if (pageInfo == null) {
            pageData.setTotal(0L);
            pageData.setList(new ArrayList<>());
            pageData.setPages(0);
            pageData.setPageNum(0);
            pageData.setPageSize(0);
        }
        pageData.setTotal(pageInfo.getTotal());
        pageData.setList(pageInfo.getList());
        pageData.setPages(pageInfo.getPages());
        pageData.setPageNum(pageInfo.getPageNum());
        pageData.setPageSize(pageInfo.getPageSize());
        return pageData;
    }
}
