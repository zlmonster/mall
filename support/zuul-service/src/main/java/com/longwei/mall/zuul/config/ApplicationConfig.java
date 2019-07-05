package com.longwei.mall.zuul.config;

import com.longwei.mall.common.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
/**
 * @author lizhilong
 *  使用环境中的配置覆盖基本配置，如果无环境配置，则获取默认配置。
 */
@Configuration

public class ApplicationConfig {
	@Autowired
	public void initUtil(ApplicationContext ctx){
		new SpringUtil().setApplicationContext(ctx);
	}
}
