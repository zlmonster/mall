package com.longwei.mall.oms.business.demo.dto.response;

import com.longwei.mall.oms.business.demo.vo.DemoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description= "查询demo")
public class QueryDemoResponse {
    @ApiModelProperty(value = "demo vo对象")
    private DemoVo demoVo;
}
