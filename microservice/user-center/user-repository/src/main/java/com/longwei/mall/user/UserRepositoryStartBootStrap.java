package com.longwei.mall.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.longwei.mall"})
public class UserRepositoryStartBootStrap {
    public static void main(String[] args) {
        SpringApplication.run(UserRepositoryStartBootStrap.class, args);
    }
}
