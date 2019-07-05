package com.longwei.mall.oms.business.demo.controller;

import com.longwei.mall.common.web.annotation.GenericResponseBody;
import com.longwei.mall.oms.business.demo.dto.request.QueryDemoRequest;
import com.longwei.mall.oms.business.demo.dto.response.QueryDemoResponse;
import com.longwei.mall.oms.business.demo.service.IDemoService;
import com.longwei.mall.oms.business.demo.vo.DemoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
/**
 * @author lizhilong
 */
@Api(tags = "Demo Controller",
        value="Demo Controller",
        description = "demo 接口")

@RestController
public class DemoController {

    @Autowired
    private IDemoService demoService;
    @ApiOperation(value = "查询会员认证信息", notes = "会员认证信息")
    @PostMapping(value = "/demo/queryDemo",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GenericResponseBody
    public QueryDemoResponse queryDemo(@RequestBody @Valid QueryDemoRequest request){
        DemoVo demoVo = demoService.queryDemo(request.getId());
        QueryDemoResponse response = new QueryDemoResponse();
        response.setDemoVo(demoVo);
        return response;
    } @ApiOperation(value = "查询会员认证信息", notes = "会员认证信息")
    @PostMapping(value = "/demo/queryDemo1",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GenericResponseBody
    public QueryDemoResponse queryDemo1(@RequestBody @Valid QueryDemoRequest request){
        DemoVo demoVo = demoService.queryDemo1(request.getId());
        QueryDemoResponse response = new QueryDemoResponse();
        response.setDemoVo(demoVo);
        return response;
    }


}
