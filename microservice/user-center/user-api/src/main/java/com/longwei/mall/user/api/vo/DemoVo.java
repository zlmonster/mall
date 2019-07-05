package com.longwei.mall.user.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "demo vo")
public class DemoVo {
    @ApiModelProperty(value="id")
    private Long id;
    @ApiModelProperty(value="用户名")
    private String userName;
}
