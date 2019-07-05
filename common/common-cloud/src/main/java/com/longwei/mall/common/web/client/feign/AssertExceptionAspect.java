package com.longwei.mall.common.web.client.feign;

import com.longwei.mall.common.web.annotation.method.GenericResponse;
import com.longwei.mall.common.web.constants.BaseResultCode;
import com.longwei.mall.common.web.exception.BusinessException;
import com.longwei.mall.common.web.exception.ErrorCodeException;
import com.longwei.mall.common.web.exception.SystemException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * feign方式调用，远程服务返回异常码断言
 */
@Aspect
@Component
public class AssertExceptionAspect {

    @Around("@annotation(assertException)")
    public Object around(ProceedingJoinPoint pjp, AssertException assertException) throws Throwable {
        Object proceed = pjp.proceed();
        if(assertException == null || assertException.isIgnore()){
            return proceed;
        }
        if (proceed == null) {
            throw new ErrorCodeException(BaseResultCode.SYSTEM_ERROR);
        } else if (proceed instanceof GenericResponse) {
            GenericResponse response = (GenericResponse) proceed;
            if (!BaseResultCode.SUCCESS.equals(response.getCode())) {
               if(BaseResultCode.SYSTEM_ERROR.equals(response.getCode())){
                   throw new SystemException(response.getCode(), response.getMessage());
               }else{
                   throw new BusinessException(response.getCode(), response.getMessage());
               }
            }
        }
        return proceed;
    }
}
