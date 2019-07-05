package com.longwei.mall.common.web.annotation.method;


import com.longwei.mall.common.web.annotation.GenericResponseBody;
import com.longwei.mall.common.web.log.MallRequestHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 通用响应体方法处理器，将方法标注为{@link @GernericResponseBody}的返回值，
 * 包装成{@link GenericResponse}返回给客户端。
 * @author  lizhilong
*/
public class GenericResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {

	public GenericResponseBodyMethodProcessor(List<HttpMessageConverter<?>> messageConverters) {
		super(messageConverters);
	}
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return false;
	};
	

	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return ((AnnotationUtils.findAnnotation(returnType.getContainingClass(), GenericResponseBody.class) != null) ||
				(returnType.getMethodAnnotation(GenericResponseBody.class) != null));
	}

	@Override
	public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws HttpMediaTypeNotAcceptableException, IOException {
		/**包装方法返回的bean*/
		final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		GenericResponse<Object> wrapper = new GenericResponse<>( returnValue);
		super.handleReturnValue(wrapper, returnType, mavContainer, webRequest);

		// 请求埋点
		MallRequestHolder.setResponse(wrapper);

	}

}
