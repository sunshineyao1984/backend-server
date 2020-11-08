package com.frog.backend.server.service.base.pojo.vo.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description 分页数据
 *
 * @author yxy
 * @date 2020-10-08
 */
@ApiModel("分页信息")
@Data
@NoArgsConstructor
public class PageData<T> {

    @ApiModelProperty("总记录数")
    protected Long total;

    @ApiModelProperty("当页数据列表")
    protected List<T> list;

    @ApiModelProperty("总页数")
    protected Integer pages;

    @ApiModelProperty("第几页")
    protected Integer pageNum;

    @ApiModelProperty("页尺寸")
    protected Integer pageSize;

    @ApiModelProperty("其它非列表数据")
    protected Object otherData;
}
