package mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;


import com.longwei.mall.oms.repository.OmsRepositoryStartBootStrap;
import com.longwei.mall.oms.repository.entity.Demo;
import com.longwei.mall.oms.repository.entity.DemoExample;
import com.longwei.mall.oms.repository.mapper.DemoMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OmsRepositoryStartBootStrap.class)
public class DemoMapperTest {
    private Logger logger = LoggerFactory.getLogger(DemoMapperTest.class);

    @Autowired
    private DemoMapper demoMapper;

    @Test
    public void selectByPrimaryKey() {
        Demo sms = demoMapper.selectByPrimaryKey(4L);
        Assert.assertNotNull(sms);
    }

    @Test
    public void selectAllByPage(){
        Page<Demo> page = PageHelper.startPage(1,5);
        DemoExample queryExmaple= new DemoExample();
        queryExmaple.createCriteria().andIdIsNotNull();
        List<Demo> list=  demoMapper.selectByExample(queryExmaple);
        System.out.println(page.getTotal());
        Assert.assertNotNull(list);
        System.out.println(list.size());
    }



}
