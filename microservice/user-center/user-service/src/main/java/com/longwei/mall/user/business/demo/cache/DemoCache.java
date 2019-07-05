package com.longwei.mall.user.business.demo.cache;

import com.longwei.mall.common.redis.single.core.CacheExpire;
import com.longwei.mall.common.util.DateUtil;
import com.longwei.mall.user.api.vo.DemoVo;
import com.longwei.mall.user.repository.entity.Demo;
import com.longwei.mall.user.repository.mapper.DemoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author lizhilong
 */
@Service
public class DemoCache {
    @Autowired
    private DemoMapper demoMapper;
    private final static String REDIS_KEY = "USER-SERVICE:DEMO";
    //    @Cacheable(value = "DEMO_CACHE:",key="#id")
//    @Cacheable(value = "DEMO_CACHE" ,key="cache[1].name")    //spring 默认使用root对象属性，#root可以省略
//    @Cacheable(value = { "DEMO_CACHE","DEMO_CACHE2" },key="targetClass.getName()+'.'+methodName+'.'+#id") //自定义  key = 类名.方法名.参数值  添加字符用单引号,样例中会添加连个缓存
    // key="#root.target.getFormatKey(#p0,#p1)"
//    @Cacheable(value = "DEMO_CACHE:",keyGenerator = "keyGenerator") // 自定义缓存key：  类名:方法名:参数
//        @Cacheable(value = "DEMO_CACHE:",key="#id",condition = "#id>3") //condition : 指定发生条件，引用SpEL表达式
//    @Cacheable(value = "DEMO_CACHE:",key="#p0")
    @Cacheable(value = REDIS_KEY,key="#id",unless="#result == null")
    @CacheExpire(expire = 60)
    public DemoVo getDemoVo(Long id){
        Demo demo = demoMapper.selectByPrimaryKey(id);
        DemoVo demoVo = null;
        if(demo != null){
            demoVo = new DemoVo();
            BeanUtils.copyProperties(demo,demoVo);
        }
        return demoVo;
    }

    public String getFormatKey(Date date, String str){//生成key
        return DateUtil.formatDate("yyyyMMdd",date) + str;//格式化时间拼接key
    }

}
