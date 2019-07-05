# 目录
```
└─microservice
    ├─bin
    │  ├─eureka-server
            └─setEnv.sh（环境变量配置）
    │  ├─marketing-service
    │  ├─message-service
    │  ├─monitor-service
    │  ├─operation-service
    │  ├─order-service
    │  ├─organization-service
    │  ├─pay-service
    │  ├─product-service
    │  ├─public-service
    │  ├─search-service
    │  ├─turbine-service
    │  ├─user-service
    │  ├─zipkin-server
    │  └─zuul-service
    │  └─setvice.sh（应用管理脚本）
    ├─libs（各自服务jar包）
    └─logs


```


# 部署指南
## 创建系统用户
[root@cmall01 ~]#  root登录  
[root@cmall01 ~]#  useradd -d /app -g users  -s /bin/bash -m cmall  
[root@cmall01 ~]#  passwd cmall  
[root@cmall01 ~]#  cmall!Q2w  
[root@cmall01 ~]#  cmall!Q2w  

## 上传部署工具
```
将microservice整目录FTP上传至服务器/app
[cmall@cmall ~]$ chmod -R +x ~/microservice/**/*.sh
```

## 发布微服务应用
```
通过maven构建打包，如：
> 构建打包user.center应用
> mvn clean package -P dev
> 将deploy/microservice/libs/user-service.jar发布到服务器/app/microservice/libs/目录下

登录cmall系统用户
[cmall@cmall ~]$ cd ~/microservice/
# 启动user-service服务
[cmall@cmall ~]$ ./bin/service.sh user-service start
# 关闭user-service服务
[cmall@cmall ~]$ ./bin/service.sh user-service stop
```
