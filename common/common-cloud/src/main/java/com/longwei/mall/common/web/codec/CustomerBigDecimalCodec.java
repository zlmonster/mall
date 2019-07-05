package com.longwei.mall.common.web.codec;

import com.alibaba.fastjson.serializer.*;

import java.text.DecimalFormat;

public class CustomerBigDecimalCodec extends BigDecimalCodec implements ContextObjectSerializer {

    public final static CustomerBigDecimalCodec instance = new CustomerBigDecimalCodec();

    /**
     * 当BigDecimal类型的属性上有@JsonFiled注解，且该注解中的format有值时，使用该方法进行序列化，否则使用fastjson的
     * BigDecimalCodec中的write方法进行序列化
     */
    @Override
    public void write(JSONSerializer serializer, Object object, BeanContext context){
        SerializeWriter out = serializer.out;
        if(object == null) {
            out.writeString("");
            return;
        }
        String format = context.getFormat();
        DecimalFormat decimalFormat = new DecimalFormat(format);
        out.writeString(decimalFormat.format(object));
    }

}