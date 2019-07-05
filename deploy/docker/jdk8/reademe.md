# Java服务Docker化

## 依赖镜像
使用docker.io上的alpine-glibc镜像

## jdk
选用oracle官网jre-8u191-linux-x64.tar.gz  （https://sdlc-esd.oracle.com/ESD6/JSCDL/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jre-8u191-linux-x64.tar.gz?GroupName=JSC&FilePath=/ESD6/JSCDL/jdk/8u191-b12/2787e4a523244c269598db4e85c51e0c/jre-8u191-linux-x64.tar.gz&BHost=javadl.sun.com&File=jre-8u191-linux-x64.tar.gz&AuthParam=1543851018_c32a4bf3dc8ce933cbae390410393dcd&ext=.gz） 
 
## 构建镜像
将jre-8u191-linux-x64.tar.gz和Dockerfile上传至服务器。
```
注意： 不同环境需要分别构建

```
# 开发环境
```
# 构建镜像
# docker build -t 192.168.85.9:8010/docker-dev/jdk8:latest ./
# 测试验证
# docker run -it 192.168.85.9:8010/docker-dev/jdk8:latest
进入容器： java -version

# 登录nexus docker私服
# docker login 192.168.85.9:8010

# docker push 192.168.85.9:8010/docker-dev/jdk8:latest

```

# 测试环境
```
# 构建镜像
# docker build -t 192.168.85.9:8011/docker-test/jdk8:latest ./
# 测试验证
# docker run -it 192.168.85.9:8011/docker-test/jdk8:latest
进入容器： java -version

# 登录nexus docker私服
# docker login 192.168.85.9:8011

# docker push 192.168.85.9:8011/docker-test/jdk8:latest

```
# 灰度环境
```
# 构建镜像
# docker build -t 192.168.85.9:8011/docker-test/jdk8:latest ./
# 测试验证
# docker run -it 192.168.85.9:8011/docker-test/jdk8:latest
进入容器： java -version

# 登录nexus docker私服
# docker login 192.168.85.9:8011

# docker push 192.168.85.9:8011/docker-test/jdk8:latest

```
# 生产环境
```
# 构建镜像
# docker build -t 192.168.85.9:8011/docker-test/jdk8:latest ./
# 测试验证
# docker run -it 192.168.85.9:8011/docker-test/jdk8:latest
进入容器： java -version

# 登录nexus docker私服
# docker login 192.168.85.9:8011

# docker push 192.168.85.9:8011/docker-test/jdk8:latest

```
