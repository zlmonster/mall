package com.longwei.mall.common.util.business;



import com.longwei.mall.common.util.DateUtil;
import com.longwei.mall.common.util.StringUtil;

import java.util.Date;

public class IdCardUtil {
    /**
     * 18位身份证号码各位的含义:
     *
     * 1-2位省、自治区、直辖市代码；
     *
     * 3-4位地级市、盟、自治州代码；
     *
     * 5-6位县、县级市、区代码；
     *
     * 7-14位出生年月日，比如19670401代表1967年4月1日；
     *
     * 15-17位为顺序号，其中17位（倒数第二位）男为单数，女为双数；
     * 18位为校验码，0-9和X。作为尾号的校验码，是由把前十七位数字带入统一的公式计算出来的，计算的结果是0-10，如果某人的尾号是0－9，都不会出现X，
     * 但如果尾号是10，那么就得用X来代替，因为如果用10做尾号，那么此人的身份证就变成了19位。X是罗马数字的10，用X来代替10。
     *
     *
     * 15位身份证号码各位的含义:
     * 1-2位省、自治区、直辖市代码；
     * 3-4位地级市、盟、自治州代码；
     * 5-6位县、县级市、区代码；
     * 7-12位出生年月日,比如670401代表1967年4月1日,与18位的第一个区别；
     * 13-15位为顺序号，其中15位男为单数，女为双数；
     * 与18位身份证号的第二个区别：没有最后一位的验证码。
     * 举例：
     * 130503 670401 001的含义; 13为河北，05为邢台，03为桥西区，出
     * 生日期为1967年4月1日，顺序号为001。
     */
    /**
     * 校验身份证
     *
     * @param idcard
     * @return
     */
    public static boolean checkIdCard(String idcard) {
        if (!StringUtil.isNullStr(idcard)) {
            // 二代身份证18位
            if (idcard.matches("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$")) {
                return true;
            } else {
                if (idcard.matches("^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$")) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public static Date getBirthday(String idcard){
        if(checkIdCard(idcard) == false){
            return null;
        }
        //二代身份证
        if(idcard.length()==18){
            String birthday = idcard.substring(6, 14);
            return DateUtil.parseDate("yyyyMMdd", birthday);
        }else if(idcard.length() == 15){
            String birthday = "19" + idcard.substring(6, 12);
            return DateUtil.parseDate("yyyyMMdd", birthday);
        }
        return null;
    }

    /**
     *
     * @param idcard
     * @return 0: 女， 1：男
     */
    public static String getSex(String idcard){
        if(checkIdCard(idcard) == false){
            return null;
        }
        int sex = -1;
        //二代身份证
        if(idcard.length()==18){
            sex = StringUtil.nullToInteger(idcard.charAt(16));
        }else if(idcard.length() == 15){
            sex = StringUtil.nullToInteger(idcard.charAt(14));
        }
        if(sex > 0  ){
            if(sex % 2 == 0)
            {
                return "0";//女
            }else{
                return "1";//男
            }
        }
        return null;
    }

}
