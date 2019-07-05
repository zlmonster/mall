package com.longwei.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author lizhilong
 */
@SpringBootApplication(scanBasePackages="com.longwei.mall")
//@EnableSwaggerButler
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableZuulProxy
@ServletComponentScan(basePackages = { "com.longwei.mall"})
public class ZuulServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(ZuulServiceApplication.class, args);
	}
}
