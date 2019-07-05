package com.longwei.mall.zuul.filter;

import com.longwei.mall.common.web.constants.BaseResultCode;
import com.netflix.zuul.context.RequestContext;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;
/**
 * @author lizhilong
 */
@Component
public class ZuulErrorAttribute extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable t =  (Throwable)ctx.get("error.exception");
        Map<String, Object> result = super.getErrorAttributes(webRequest, includeStackTrace);
        result.put("code", BaseResultCode.SYSTEM_ERROR);
        result.put("message","system error");
        result.put("result",null);
        return result;

    }


}