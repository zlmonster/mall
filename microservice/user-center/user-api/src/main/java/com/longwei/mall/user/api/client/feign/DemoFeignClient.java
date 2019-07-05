package com.longwei.mall.user.api.client.feign;

import com.longwei.mall.common.web.annotation.method.GenericResponse;
import com.longwei.mall.common.web.client.feign.AssertException;
import com.longwei.mall.user.api.client.feign.fallback.DemoFeignClientFallback;
import com.longwei.mall.user.api.client.mapping.DemoMapping;
import com.longwei.mall.user.api.constants.UserServiceInstanceInfo;
import com.longwei.mall.user.api.dto.request.QueryDemoRequest;
import com.longwei.mall.user.api.dto.response.QueryDemoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author lizhilong
 */
@FeignClient(name = UserServiceInstanceInfo.SERVICE_NAME, fallback = DemoFeignClientFallback.class)
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
