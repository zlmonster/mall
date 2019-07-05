package com.longwei.mall;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = { "com.longwei.mall"})
@EnableTurbine
@EnableHystrixDashboard
public class TurbineApplication {

	public static void main(String[] args) {

			new SpringApplicationBuilder(TurbineApplication.class).web(true).run(args);
	}
}
