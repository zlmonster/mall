package com.longwei.mall.common.web.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


public class RequestUtil {

    public static String getClientType(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        String clientType = httpServletRequest.getHeader("x-client-type");
        if(StringUtils.isEmpty(clientType)){
            clientType = httpServletRequest.getParameter("x_client_type");
        }
        return clientType;
    }
    public static String getDeviceNo(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        String clientType = httpServletRequest.getHeader("x-deviceno");
        if(StringUtils.isEmpty(clientType)){
            clientType = httpServletRequest.getParameter("x_deviceno");
        }
        return clientType;
    }
    public static String getToken() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        String token = httpServletRequest.getHeader("x-token");
        if(StringUtils.isEmpty(token)){
            token = httpServletRequest.getParameter("x_token");
        }
        return token;
    }
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return  requestAttributes.getRequest();
    }





}
