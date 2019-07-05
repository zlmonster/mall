package com.longwei.mall.common.web.exception.handler;


import com.longwei.mall.common.web.annotation.method.GenericResponse;
import com.longwei.mall.common.web.constants.BaseResultCode;
import com.longwei.mall.common.web.exception.BusinessException;
import com.longwei.mall.common.web.exception.SystemException;
import com.longwei.mall.common.web.log.LoggerUtils;
import com.longwei.mall.common.web.log.MallRequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ControllerAdviceProcessor {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    protected MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public GenericResponse<?> handleException(HttpServletRequest request, Exception ex) {

        String code = BaseResultCode.SYSTEM_ERROR;
        if(ex instanceof HttpMessageNotReadableException){
            code = BaseResultCode.PARAM_ERROR;
        }else if(ex instanceof HttpRequestMethodNotSupportedException){
            code = BaseResultCode.HTTP_METHOD_NOT_SURPPORT;
        }
        String msg = null;
        if(ex instanceof BusinessException)
        {
            msg = ((BusinessException) ex).getErrorMessage();
            code = ((BusinessException)ex).getErrorCode();
        }else if(ex instanceof SystemException)
        {
            msg = ((SystemException) ex).getErrorMessage();
            code = ((SystemException)ex).getErrorCode();
        }else if(ex instanceof org.springframework.web.bind.MethodArgumentNotValidException){
            BindingResult br = ((org.springframework.web.bind.MethodArgumentNotValidException)ex).getBindingResult();
            code = parseCodeFromFieldErrors(br.getFieldErrors());
        }

        if(msg == null)
        {
            msg = getMsgByException(code, ex, messageSource);
        }
        GenericResponse<?> resp = new GenericResponse<>();
        resp.setCode(code);
        resp.setMessage(msg);
        if(logger.isDebugEnabled()){
            logger.debug("code: "+ code +"  msg: " + msg, ex);
        }

        // 记录错误日志
        LoggerUtils.ERROR_LOGGER.error("code: "+ code +"  msg: " + msg, ex);

        // 请求埋点
        MallRequestHolder.setResponse(resp);

        return resp;
    }

    private String parseCodeFromFieldErrors(List<FieldError> fieldErrors) {
        if(fieldErrors == null ||fieldErrors.isEmpty()){
            return BaseResultCode.SYSTEM_ERROR;
        }
        for(FieldError error: fieldErrors){
            String code = error.getDefaultMessage();
            if(code !=null && code.length()>0){
                return code;
            }
        }
        return BaseResultCode.SYSTEM_ERROR;
    }


    private String getMsgByException(String code, Throwable ex, MessageSource messageSource) {
        try {
            return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        }
        catch (NoSuchMessageException e) {
        }

        return "";
    }
}
