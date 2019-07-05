package com.longwei.mall.common.web.annotation.method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * GenericResponseBodyMethodProcessor注册器，将GenericResponseBodyMethodProcessor注册到RequestMappingHandlerAdapter
*/
@Component
public class GenericResponseBodyMethodProcessorRegister implements InitializingBean {
	private static final Logger LOG = LoggerFactory.getLogger(GenericResponseBodyMethodProcessorRegister.class);

	@Autowired
	private RequestMappingHandlerAdapter adapter;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info("begin afterPropertiesSet");
		List<HttpMessageConverter<?>> messageConverters = adapter.getMessageConverters();
		List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<>(adapter.getReturnValueHandlers());
		
		/**将GenericResponseBodyMethodProcessor注册到RequestMappingHandlerAdapter*/
		GenericResponseBodyMethodProcessor processor = new GenericResponseBodyMethodProcessor(messageConverters);
		returnValueHandlers.add(0, processor);
		adapter.setReturnValueHandlers(returnValueHandlers);
		LOG.info("end afterPropertiesSet");

	}
}
