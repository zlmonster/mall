package com.longwei.mall;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName EurekaServerConfig
 * @Description
 * @Author lizhilong
 * @Date 2019-07-03 17:25
 * @ModifyDate 2019-07-03 17:25
 * @Version 1.0
 */

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackageClasses = {EurekaServerConfig.class})
@ServletComponentScan(basePackageClasses = {EurekaServerConfig.class})
public class EurekaServerConfig {
}
