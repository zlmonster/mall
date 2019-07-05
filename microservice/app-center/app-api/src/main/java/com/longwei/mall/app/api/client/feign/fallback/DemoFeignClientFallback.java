package com.longwei.mall.app.api.client.feign.fallback;

import com.longwei.mall.common.web.annotation.method.GenericResponse;
import com.longwei.mall.common.web.constants.BaseResultCode;
import com.longwei.mall.common.web.exception.SystemException;
import com.longwei.mall.app.api.client.feign.DemoFeignClient;
import com.longwei.mall.app.api.dto.request.QueryDemoRequest;
import com.longwei.mall.app.api.dto.response.QueryDemoResponse;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

/**
 * demo 微服务熔断器实现
 * @author lizhilong
 */
@Component("APPDemoFeignClient")
public class DemoFeignClientFallback implements DemoFeignClient {
    @Override
    public GenericResponse<QueryDemoResponse> queryDemoInfo( @Valid QueryDemoRequest request){
        throw new SystemException(BaseResultCode.SYSTEM_ERROR);
    }
}
