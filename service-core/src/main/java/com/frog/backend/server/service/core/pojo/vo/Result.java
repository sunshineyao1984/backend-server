package com.frog.backend.server.service.core.pojo.vo;

import com.frog.backend.server.service.core.enums.CodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description 响应JSON标准模型
 *
 * @author yxy
 * @date 2020-10-08
 */
@ApiModel("响应数据")
@Data
@NoArgsConstructor
public class Result<T> extends BaseResult {

    @ApiModelProperty("响应业务数据")
    protected T data;

    public Result(CodeEnum codeEnum, T data) {
        super(codeEnum);
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }
}
