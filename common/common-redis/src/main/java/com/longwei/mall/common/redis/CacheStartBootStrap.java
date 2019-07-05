package com.longwei.mall.common.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.longwei.mall"})
public class CacheStartBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(CacheStartBootStrap.class, args);
    }


}
