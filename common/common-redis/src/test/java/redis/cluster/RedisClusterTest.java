package redis.cluster;

import com.longwei.mall.common.redis.CacheStartBootStrap;
import com.longwei.mall.common.redis.cluster.JedisClusterCache;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheStartBootStrap.class)
public class RedisClusterTest {
    @Autowired
    private JedisClusterCache jedisClusterCache;
    @Test
    public void selectByPrimaryKey() {

        for(int i=0;i<1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    jedisClusterCache.set("test"+new Random().nextInt(10000000),"test");
                }
            }).start();
        }

        System.out.println(jedisClusterCache.get("test"));
    }
}
