package com.frog.backend.server.service.core.pojo.vo;

import com.frog.backend.server.service.core.enums.CodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description 响应JSON基本模型
 *
 * @author yxy
 * @date 2020-10-08
 */
@ApiModel("基本响应数据")
@Data
@NoArgsConstructor
public class BaseResult implements Serializable {

    @ApiModelProperty("响应码")
    protected Integer code;

    @ApiModelProperty("响应信息")
    protected String msg;

    /**
     * 接口执行时间，其值通过AOP计算获取，所以在构造方法中不予赋值
     */
    @ApiModelProperty("接口执行时间")
    protected Long cost;

    public BaseResult(CodeEnum codeEnum){
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public BaseResult(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
