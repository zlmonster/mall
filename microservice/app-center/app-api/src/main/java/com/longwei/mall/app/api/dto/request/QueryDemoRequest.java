package com.longwei.mall.app.api.dto.request;

import com.longwei.mall.app.api.constants.AppServiceResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
/**
 * @author lizhilong
 */
@Data
@ApiModel(description= "查询demo")
public class QueryDemoRequest {
    @ApiModelProperty(value = "demo id")
    @NotNull(message = AppServiceResultCode.DEMO_ID_NOT_NULL)
    private Long id;
}
