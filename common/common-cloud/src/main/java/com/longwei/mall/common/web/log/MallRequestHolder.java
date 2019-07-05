package com.longwei.mall.common.web.log;

import com.longwei.mall.common.web.annotation.method.GenericResponse;
import org.springframework.core.NamedInheritableThreadLocal;

/**
 * request 请求处理持有者
 * @author lizhilong
 */
public class MallRequestHolder {
    /**
     * 每个请求处理后的响应
     */
    private final static ThreadLocal<GenericResponse> responseHolder = new NamedInheritableThreadLocal<>("GenericeResponse");

    public static void setResponse(GenericResponse response){
        responseHolder.set(response);
    }
    public static GenericResponse getResponse(){
        return responseHolder.get();
    }
    public static void removeResponse(){
        responseHolder.remove();
    }


}
