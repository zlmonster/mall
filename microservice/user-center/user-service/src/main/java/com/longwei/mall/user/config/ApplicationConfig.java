package com.longwei.mall.user.config;

import com.longwei.mall.common.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//使用环境中的配置覆盖基本配置，如果无环境配置，则获取默认配置。
@PropertySource(value = {"classpath:/config/jdbc.properties",
        "classpath:/config/jdbc-${spring.profiles.active}.properties"})
public class ApplicationConfig {
	@Autowired
	public void initUtil(ApplicationContext ctx){
		new SpringUtil().setApplicationContext(ctx);
	}
}
