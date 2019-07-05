package com.longwei.mall.common.web.annotation.method;

import com.longwei.mall.common.web.constants.BaseResultCode;
import lombok.Data;

/**
 * 通用响应
 * @param <T>
 */
@Data
public class GenericResponse<T> {

    // 错误类型
    private String code;
    private String message;
    private T result;


    public GenericResponse() {
        super();
    }

    public GenericResponse(T result) {
        this(BaseResultCode.SUCCESS, BaseResultCode.SUCCESS_MSG, result);
    }

    public GenericResponse(String code,
                           String message, T result) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
    }
}
