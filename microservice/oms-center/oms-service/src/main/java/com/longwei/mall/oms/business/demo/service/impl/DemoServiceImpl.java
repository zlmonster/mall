package com.longwei.mall.oms.business.demo.service.impl;

import com.longwei.mall.common.redis.single.core.CacheExpire;
import com.longwei.mall.common.redis.single.core.RedisUtil;
import com.longwei.mall.oms.business.demo.service.IDemoService;
import com.longwei.mall.oms.business.demo.vo.DemoVo;
import com.longwei.mall.oms.repository.entity.Demo;
import com.longwei.mall.oms.repository.mapper.DemoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author lizhilong
 */
@Service
@Slf4j
public class DemoServiceImpl implements IDemoService {
    @Autowired
    private DemoMapper demoMapper;

    @Override
//    @Cacheable(value = "DEMO_CACHE:",key="#id")
//    @Cacheable(value = "DEMO_CACHE" ,key="cache[1].name")    //spring 默认使用root对象属性，#root可以省略

//     key 和
//    @Cacheable(value = { "DEMO_CACHE","DEMO_CACHE2" },key="targetClass.getName()+'.'+methodName+'.'+#id") //自定义  key = 类名.方法名.参数值  添加字符用单引号 ,样例中会添加连个缓存
//    @Cacheable(value = "DEMO_CACHE:",keyGenerator = "keyGenerator") // 自定义缓存key：  类名:方法名:参数
//        @Cacheable(value = "DEMO_CACHE:",key="#id",condition = "#id>3") //condition : 指定发生条件，引用SpEL表达式
//    @Cacheable(value = "DEMO_CACHE:",key="#p0")
    @Cacheable(value = "DEMO_CACHE:",key="#id")
    @CacheExpire(expire = 30L)
    public DemoVo queryDemo(Long id) {

        Demo demo = demoMapper.selectByPrimaryKey(id);
        if(demo!=null){
            DemoVo demoVo = new DemoVo();
            BeanUtils.copyProperties(demo, demoVo);
            return demoVo;
        }
        return null;
    }

    @Override
    public DemoVo queryDemo1(Long id) {
        if(RedisUtil.hasKey("DEMO:"+id)){
            return RedisUtil.get("DEMO:"+id, DemoVo.class);
        }
        Demo demo = demoMapper.selectByPrimaryKey(id);
        if(demo!=null){
            DemoVo demoVo = new DemoVo();
            BeanUtils.copyProperties(demo, demoVo);
            RedisUtil.set("DEMO:"+id, demoVo);
            RedisUtil.expire("DEMO:"+id,60L, TimeUnit.MINUTES);
            return demoVo;
        }
        return null;
    }


    /**
     * CacheEvict 删除的数据，要将缓存中的信息清除
     */
    @Override
    @CacheEvict(value = "DEMO_CACHE:",key="#id")
    public void deleteDemo(Long id) {

    }

    /**
     * CachePut 每次都触发真实的方法调用，将执行结果放入缓存中
     * update 和 insert 实现方式一样
     * @param id
     */
    @Override
    @CachePut(value = "DEMO_CACHE:",key="#id")
    @CacheExpire(expire = 30L)
    public DemoVo insertOrUpdateDemo(Long id) {
        Demo demo = demoMapper.selectByPrimaryKey(id);
        if(demo!=null){
            DemoVo demoVo = new DemoVo();
            BeanUtils.copyProperties(demo, demoVo);
            return demoVo;
        }
        return null;
    }
}
