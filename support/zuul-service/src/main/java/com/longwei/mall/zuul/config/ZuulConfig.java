package com.longwei.mall.zuul.config;

import com.longwei.mall.zuul.filter.PostFilter;
import com.longwei.mall.zuul.filter.AccessFilter;
import com.longwei.mall.zuul.filter.ErrorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author lizhilong
 */
@Configuration
public class ZuulConfig {
    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }

    @Bean
    public ErrorFilter errorFilter(){
        return new ErrorFilter();
    }

    @Bean
    public PostFilter postFilter(){
        return new PostFilter();
    }
}
