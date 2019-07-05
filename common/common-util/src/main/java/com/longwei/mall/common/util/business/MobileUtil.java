package com.longwei.mall.common.util.business;


import com.longwei.mall.common.util.StringUtil;

public class MobileUtil {
    public static boolean checkMobile(String mobile){
        if(!StringUtil.isNullStr(mobile)){
            if(mobile.matches("^1[0-9]{10}$")){
                return true;
            }
        }
        return false;
    }
}
