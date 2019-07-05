package com.longwei.mall.common.web.constants;

public class BaseResultCode {
    /*系统异常码*/
    public static final String SUCCESS = "200";
    public static final String SUCCESS_MSG = "success";

    public static final String UNKOWN_ERROR = "404";
    public static final String UNKOWN_ERROR_MSG = "unkown error";

    public static final String SYSTEM_ERROR = "500";
    public static final String SYSTEM_BUSI= "502";
    public static final String SYSTEM_ERROR_MSG = "system error";




    /*通用业务异常码*/
    public static final String PARAM_ERROR = "900";//参数错误
    public static final String HTTP_METHOD_NOT_SURPPORT = "901";//HTTP方法不支持
    public static final String GET_REDIS_LOCK_ERROR = "902";//获取redis分布式锁失败
    public static final String NO_PERMISSIONS = "903";//无权访问

    public static final String UNKOWN_CLIENT_TYPE = "904";//客户端类型错误

    public static final String OPERATE_ERROR = "905";//操作错误
}
