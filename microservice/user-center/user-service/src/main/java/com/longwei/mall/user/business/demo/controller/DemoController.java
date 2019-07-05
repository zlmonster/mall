package com.longwei.mall.user.business.demo.controller;

import com.longwei.mall.common.web.annotation.GenericResponseBody;
import com.longwei.mall.user.api.client.mapping.DemoMapping;
import com.longwei.mall.user.api.dto.request.QueryDemoRequest;
import com.longwei.mall.user.api.dto.response.QueryDemoResponse;
import com.longwei.mall.user.api.vo.DemoVo;
import com.longwei.mall.user.business.demo.service.IDemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author  lizhilong
 */
@Api(tags = "Demo Controller",
        value="Demo Controller",
        description = "Demo微服务接口")
@Validated
@RestController
@Slf4j
public class DemoController {

    @Autowired
    private IDemoService demoService;

    @ApiOperation(value = "查询demo", notes = "查询demo")
    @PostMapping(value = DemoMapping.QUERY_DEMO,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GenericResponseBody
    public QueryDemoResponse selectCustomerAddressList(@RequestBody @Valid  QueryDemoRequest request) {
        DemoVo demoVo = demoService.queryDemo(request.getId());
        QueryDemoResponse response = new QueryDemoResponse();
        response.setDemoVo(demoVo);
        return response;
    }

}
