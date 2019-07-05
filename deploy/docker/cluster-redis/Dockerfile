# Redis
# Version 4.0.9
FROM ubuntu:14.04
MAINTAINER lizhilong <974213337@qq.com>
ADD redis-4.0.9.tar.gz /usr/local
RUN mkdir /data
ADD redis.conf /data
RUN apt-get -y update
RUN apt-get install -y gcc make curl apt-utils wget unzip
RUN cd /usr/local/redis-4.0.9/src
WORKDIR /usr/local/redis-4.0.9/src
RUN make install
RUN cd /data
WORKDIR /data
EXPOSE 6379
ENTRYPOINT redis-server redis.conf