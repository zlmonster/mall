package com.longwei.mall.user.api.constants;


import com.longwei.mall.common.web.constants.BaseResultCode;

/**
 * 用户中心响应码常量类
 */
public class UserServiceResultCode extends BaseResultCode {
    /**
     * 返回码前缀
     */
    private static final String SERVICE_ID = "usr"+"_";

    /**
     * demo id非空
     */
    public static final String DEMO_ID_NOT_NULL = SERVICE_ID + "1000";

}
