package com.longwei.mall.common.util.business;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longwei.mall.common.util.HttpUtil;
import com.longwei.mall.common.util.MD5Util;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * @Description:
 * @author: 51667
 * @Date: 2019-04-12
 * @Time: 10:30
 */
@Slf4j
public class KuaiDiUtil {

    private static final String customerId = "2071D811F9AB2A28E961D2686D3100ED";
    private static final String key = "XPfpQGLE1148";

    /**
     * 快递单当前签收状态，包括0在途中、1已揽收、2疑难、3已签收、4退签、5同城派送中、6退回、7转单等7个状态
     */
    public static final String THREE="3";
    public static final String STATE="state";
    public static final String STATUS="status";
    public static final String DATA="data";
    public static final String MESSAGE="message";
    public static final String OK="ok";

    public static final String RETURN_CODE="returnCode";
    public static final String RESULT="result";
    public static final String FALSE="false";
    public static final String FIVE_HUNDRED="500";
    public static final String TWO_HUNDRED="200";

/*    {
        "message": "ok",
            "nu": "3853400054767",
            "ischeck": "1",
            "condition": "D01",
            "com": "yunda",
            "status": "200",
            "state": "3",
            "data": [{
            "time": "2019-04-10 10:42:30",
                "ftime": "2019-04-10 10:42:30",
                "context": "【杭州市】 已签收 : 由xxx代签收，如有问题联系张俊峰(19857022721)"
    }]
    }*/
    public static String query(String kuaiDiCode,String expressNo) {
        String resp = null;
        JSONObject req = new JSONObject();
        req.put("com", kuaiDiCode);
        //num:  运单号
        req.put("num", expressNo);
        String param = req.toJSONString();
        String singParam = param + key + customerId;
        try {
            //com： 物流公司编码
            String sign = MD5Util.MD5Encode(singParam, "utf-8");
            HashMap params = new HashMap();
            params.put("param", param);
            params.put("sign", sign.toUpperCase());
            params.put("customer", customerId);
            resp = HttpUtil.javahttpPost(params, "http://poll.kuaidi100.com/poll/query.do");
            log.info("快递100物流查询成功：req={},resp={}", JSON.toJSONString(params), resp);
            //{"result":false,"returnCode":"500","message":"查询无结果，请隔段时间再查"}
            //{"result":false,"returnCode":"400","message":"不支持此快递公司"}
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("快递100物流查询失败：req=");
            sb.append(param).append(";resp=").append(resp);
            log.error("快递100物流查询失败："+sb.toString(),e);
        }
        return resp;
    }
}
