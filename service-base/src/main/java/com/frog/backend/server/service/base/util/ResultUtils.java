package com.frog.backend.server.service.base.util;

import com.frog.backend.server.service.base.pojo.vo.data.PageData;
import com.frog.backend.server.service.core.enums.CodeEnum;
import com.frog.backend.server.service.core.pojo.vo.BaseResult;
import com.frog.backend.server.service.core.pojo.vo.Result;
import com.github.pagehelper.PageInfo;

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
     * @param codeEnum
     * @return
     */
    public static BaseResult returnBaseResultError(CodeEnum codeEnum) {
        if (codeEnum == null) {
            return new BaseResult(CodeEnum.ERROR);
        }
        return new BaseResult(codeEnum);
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
        return new BaseResult(CodeEnum.DYNAMIC_CODE.setDynamic(codeEnum, msg));
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
        return new BaseResult(CodeEnum.DYNAMIC_CODE.setDynamicCode(code, msg));
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
        return new Result<>(CodeEnum.DYNAMIC_CODE.setDynamic(codeEnum, msg), data);
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
        return new Result<>(CodeEnum.DYNAMIC_CODE.setDynamicCode(code, msg), data);
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
        return new Result<>(CodeEnum.DYNAMIC_CODE.setDynamicCode(baseResult.getCode(), baseResult.getMsg()), data);
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
        pageData.setTotal(pageInfo.getTotal());
        pageData.setList(pageInfo.getList());
        pageData.setPages(pageInfo.getPages());
        pageData.setPageNum(pageInfo.getPageNum());
        pageData.setPageSize(pageInfo.getPageSize());
        return pageData;
    }
}
