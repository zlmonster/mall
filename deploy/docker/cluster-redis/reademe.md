# redis服务Docker化
该镜像是redis基础镜像，并非可执行镜像。

## 依赖镜像
centos:7

## redis版本
redis-4.0.9.tar.gz
## 构建镜像
将redis-4.0.9.tar.gz，redis.conf，Dockerfile上传至服务器。

```
注意： 不同环境需要分别构建

```
# 开发环境
```
# 以开发环境为例
# 构建镜像
# docker build -t 192.168.85.9:8010/docker-dev/cluster-redis:latest ./


```

# 测试环境
```
# 以开发环境为例
# 构建镜像
# docker build -t 192.168.85.9:8011/docker-test/cluster-redis:latest ./


# 测试验证
# 启动6381
# docker run -d --name redis1 -p 6381:6379 -p 16381:16379 -it \
  192.168.85.9:8011/docker-test/cluster-redis:latest

# 启动6382
# docker run -d --name redis2 -p 6382:6379 -p 16382:16379 -it \
  192.168.85.9:8011/docker-test/cluster-redis:latest

# 启动6383
# docker run -d --name redis3 -p 6383:6379 -p 16383:16379 -it \
  192.168.85.9:8011/docker-test/cluster-redis:latest

# 启动6384
# docker run -d --name redis4 -p 6384:6379 -p 16384:16379 -it \
  192.168.85.9:8011/docker-test/cluster-redis:latest

# 启动6385
# docker run -d --name redis5 -p 6385:6379 -p 16385:16379 -it \
  192.168.85.9:8011/docker-test/cluster-redis:latest

# 启动6386
# docker run -d --name redis6 -p 6386:6379 -p 16386:16379 -it \
  192.168.85.9:8011/docker-test/cluster-redis:latest

# 登录nexus docker私服
# docker login 192.168.85.9:8011
# 上传镜像
# docker push 192.168.85.9:8011/docker-test/cluster-redis:latest


# 配置集群
#  ./redis-trib.rb create --replicas 1 192.168.85.6:6381 192.168.85.6:6382 192.168.85.6:6383 192.168.85.6:6384 192.168.85.6:6385 192.168.85.6:6386

```
