package com.longwei.mall.app.api.client.feign;

import com.longwei.mall.app.api.constants.AppServiceInstanceInfo;
import com.longwei.mall.common.web.annotation.method.GenericResponse;
import com.longwei.mall.common.web.client.feign.AssertException;
import com.longwei.mall.app.api.client.feign.fallback.DemoFeignClientFallback;
import com.longwei.mall.app.api.client.mapping.DemoMapping;
import com.longwei.mall.app.api.dto.request.QueryDemoRequest;
import com.longwei.mall.app.api.dto.response.QueryDemoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author lizhilong
 */
@FeignClient(name = AppServiceInstanceInfo.SERVICE_NAME, fallback = DemoFeignClientFallback.class)
public interface DemoFeignClient {
    /**
     * 查询demo
     * @param request
     * @return
     */
    @PostMapping(value = DemoMapping.QUERY_DEMO,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @AssertException(isIgnore = false)
    GenericResponse<QueryDemoResponse> queryDemoInfo(@RequestBody @Valid QueryDemoRequest request);

}
