package com.longwei.mall.oms.repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.longwei.mall"})
public class OmsRepositoryStartBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(OmsRepositoryStartBootStrap.class, args);
    }
}
