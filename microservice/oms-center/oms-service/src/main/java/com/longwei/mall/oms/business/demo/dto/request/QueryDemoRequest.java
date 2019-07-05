package com.longwei.mall.oms.business.demo.dto.request;

import com.longwei.mall.oms.constants.OmsResultCode;
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
    @NotNull(message = OmsResultCode.DEMO_ID_IS_NULL)
    private Long id;
}
