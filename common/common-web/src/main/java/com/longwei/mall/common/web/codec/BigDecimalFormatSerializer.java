package com.longwei.mall.common.web.codec;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class BigDecimalFormatSerializer  implements ObjectSerializer {
    private final DecimalFormat decimalFormat;

    public BigDecimalFormatSerializer(DecimalFormat decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    @Override
    public void write(JSONSerializer jsonSerializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = jsonSerializer.getWriter();
        if(object == null){
            out.write("0.00");
        }else{
            BigDecimal bigDecimal = (BigDecimal)object;
            String value = decimalFormat.format(bigDecimal);
            out.write(value);
        }
    }
}
