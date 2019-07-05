package com.longwei.mall.common.web.codec;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.math.BigDecimal;

public class BigDecimalValueFilter implements ValueFilter {
    public Object process(Object object, String name, Object value) {
        if (null != value && value instanceof BigDecimal) {
            String formatvalue = String.format("%.2f", ((BigDecimal) value).doubleValue());
            return formatvalue;
        }
        return value;
    }
}