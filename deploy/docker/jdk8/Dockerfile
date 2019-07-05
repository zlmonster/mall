# 构建jdk8镜像
FROM docker.io/jeanblanchard/alpine-glibc
MAINTAINER lizhilong <974213337@qq.com>
ADD jre-8u191-linux-x64.tar.gz /usr/local/
# set env
ENV JAVA_HOME=/usr/local/jre1.8.0_191
ENV CLASSPATH=${JAVA_HOME}/lib/dt.jar:${JAVA_HOME}/lib/tools.jar
ENV PATH="${PATH}:${JAVA_HOME}/bin"