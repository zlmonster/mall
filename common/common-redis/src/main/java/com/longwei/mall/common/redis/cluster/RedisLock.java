package com.longwei.mall.common.redis.cluster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class RedisLock {
    @Autowired
    private JedisClusterCache jedisClusterCache;
    public boolean tryLock(String key, int lockSec){
        if(jedisClusterCache.exists(key)){
            return false;
        }
        Long res = jedisClusterCache.setnx(key, "1");
        jedisClusterCache.expire(key, lockSec);
        if(res == 1){
            return true;
        }
        return false;
    }

    public void unlock(String key){
        if(jedisClusterCache.exists(key)){
            jedisClusterCache.del(key);
        }
    }
}
