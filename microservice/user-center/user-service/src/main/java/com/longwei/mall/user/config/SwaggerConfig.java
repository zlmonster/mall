package com.longwei.mall.user.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    protected Environment env;

    private final static String[] pathArray = new String[]{
      "/demo/.*"
    };

    /**
     * 可以定义多个组
     *
     */
    @Bean
    public Docket createDocket() {


        Predicate[] predicates = null;
        if(pathArray.length > 0){
            predicates = new Predicate[pathArray.length];
            for(int i =0;i<pathArray.length;i++){
                String pathRegex = pathArray[i];
                predicates[i]= PathSelectors.regex(pathRegex);
            }
        }

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(ApiInfo())
            .forCodeGeneration(true)
            .directModelSubstitute(java.nio.ByteBuffer.class, String.class)
            .select()
//            .paths(PathSelectors.any())
            .paths(Predicates.or(predicates))//过滤的接口
            .build();

        return docket;

    }


    private ApiInfo ApiInfo() {
        String appName = env.getProperty("spring.application.name");
        if(appName ==null){
            appName="Demo rest api";
        }

        return new ApiInfoBuilder()
            .title(appName)//大标题
            .description(appName + " RESTful API文档")//详细描述
            .version("1.0")//版本
            .termsOfServiceUrl("Hangzhou LongWei Technology Co., Ltd.")
            .contact(new Contact("Hangzhou LongWei Technology Co., Ltd.", "", "974213337@qq.com"))//作者
            .license("The Apache License, Version 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .build();
    }

}