package com.longwei.mall.common.web.client.feign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * springcloud feign调用，远程服务返回异常码触发
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AssertException {
    /**
     * 是否忽略异常
     * @return
     */
    boolean isIgnore() default false;
}
