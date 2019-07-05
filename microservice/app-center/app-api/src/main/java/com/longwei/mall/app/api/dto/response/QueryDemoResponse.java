package com.longwei.mall.app.api.dto.response;

import com.longwei.mall.app.api.vo.DemoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="查询demo")
@Data
public class QueryDemoResponse {
    @ApiModelProperty(value="demo vo",required = true)
    private DemoVo demoVo;
}
