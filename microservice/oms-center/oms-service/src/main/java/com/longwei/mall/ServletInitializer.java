package com.longwei.mall;

import com.longwei.mall.OmsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OmsApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(OmsApplication.class, args);
	}


}
