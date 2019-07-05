package com.longwei.mall.common.web.log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * restful api 请求统计
 */
@Data
public class RestfulApiAccessInfo {

    public RestfulApiAccessInfo(HttpServletRequest request) {
        uri = request.getRequestURI();
        this.startTime = new Date();
        this.localIp = request.getLocalAddr();
//        this.header = convertHeader(request);
    }
    /**
     * 请求地址
     */
    private String uri;

    /**
     * 请求头信息
     */
//    private Map<String, String> header;

    /**
     * 本地ip
     */
    private String localIp;

    /**
     * 开始时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss SSS")
    private Date startTime;

    /**
     * 结束时间
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss SSS")
    private Date endTime;

    /**
     * 接口返回码
     */
    private String resultCode;

    /**
     * 处理时间
     */
    private long cost;


    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteDateUseDateFormat);
    }


    private Map<String, String> convertHeader(HttpServletRequest request){
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
}
