package com.longwei.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * @author lizhilong
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ZuulServiceApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}


}
