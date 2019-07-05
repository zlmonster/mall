package com.longwei.mall.common.util.business;


import com.longwei.mall.common.util.StringUtil;

public class AccountNameUtil {
    /**
     * 校验账号
     * @param name
     */
    public static boolean checkName4Exam(String name) {
        if(!StringUtil.isNullStr(name)){
            if(name.matches("[a-zA-Z][a-zA-Z_]{2,11}")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    /**
     * 校验账号
     * @param name
     */
    public static boolean checkName4Supervise(String name) {
        if(!StringUtil.isNullStr(name)){
            if(name.matches("[a-zA-Z0-9]{6,20}")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
