package com.longwei.mall.common.redis.cluster.config;


import com.longwei.mall.common.redis.cluster.JedisClusterCache;
import com.longwei.mall.common.redis.serialize.kryo.KryoSerializer;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

//@Configuration
public class JedisClusterConfig {

    @Value("${redis.nodes}")
    private String clusterNodes;
    @Value("${redis.connectionTimeout}")
    private int connectionTimeout;
    @Value("${redis.soTimeout}")
    private int soTimeout;
    @Value("${redis.maxRedirections}")
    private int maxRedirections;

    @Value("${redis.maxActive}")
    private int maxActive;
    @Value("${redis.maxIdle}")
    private int maxIdle;
    @Value("${redis.maxWait}")
    private int maxWait;
    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Autowired
    private KryoSerializer kryoSerializer;


    @Bean("jedisClusterCache")
    public JedisClusterCache getJedisCluster() {

        String[] serverArray = clusterNodes.split(",");//获取服务器数组
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxIdle(maxIdle);
        config.setMaxWaitMillis(maxWait);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);

        return new JedisClusterCache(nodes, connectionTimeout, soTimeout, maxRedirections, config, kryoSerializer);
    }

//    @Bean("redissonClient")
    public RedissonClient createRedissonClient(){
        String[] serverArray = clusterNodes.split(",");//获取服务器数组
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000); // cluster state scan interval in milliseconds

        for(String ipPort: serverArray) {
            config.useClusterServers().addNodeAddress("redis://"+ipPort);
        }
        return Redisson.create(config);
    }
}