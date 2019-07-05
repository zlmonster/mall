package redis.single;

import com.longwei.mall.common.redis.CacheStartBootStrap;
import com.longwei.mall.common.redis.single.core.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CacheStartBootStrap.class)
public class RedisSingleTest {
    @Test
    public void selectByPrimaryKey() {
        RedisUtil.set("test","test");
        System.out.println(RedisUtil.get("test",String.class));
    }
}
