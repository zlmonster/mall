package com.longwei.mall.common.util.business;


import com.longwei.mall.common.util.StringUtil;

public class NameUtil {

    /**
     * 校验中文名字
     * @param name
     */
    public static boolean checkName(String name) {
        if(!StringUtil.isNullStr(name)){
            if(name.matches("[\\u4e00-\\u9fa5]{2,10}")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
