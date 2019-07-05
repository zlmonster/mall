package com.longwei.mall.common.util.dozer;

import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * javabean映射工具配置
 * @author xxx
 *
 */
@Configuration
@Slf4j
public class DozerBeanMapperConfigure {

	
	@Bean
    public DozerBeanMapper mapper() {
		log.info("buiding DozerBeanMapper start....");
        DozerBeanMapper mapper = new DozerBeanMapper();
        log.info("buiding DozerBeanMapper end....");
        return mapper;
    }
	
	public BeanMappingBuilder beanMappingBuilder(){
		BeanMappingBuilder beanMappingBuilder = new BeanMappingBuilder() {
	        @Override
	        protected void configure() {
	        	
	        }
		};
		return beanMappingBuilder;
	}
}
