package com.longwei.mall.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.longwei.mall"})
public class AppRepositoryStartBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(AppRepositoryStartBootStrap.class, args);
    }
}
