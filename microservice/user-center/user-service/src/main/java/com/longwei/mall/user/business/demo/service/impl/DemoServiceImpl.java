package com.longwei.mall.user.business.demo.service.impl;

import com.longwei.mall.common.web.exception.BusinessException;
import com.longwei.mall.user.api.constants.UserServiceResultCode;
import com.longwei.mall.user.api.vo.DemoVo;
import com.longwei.mall.user.business.demo.cache.DemoCache;
import com.longwei.mall.user.business.demo.service.IDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lizhilong
 */
@Service
@Slf4j
public class DemoServiceImpl implements IDemoService {
    @Autowired
    private DemoCache demoCache;
    @Override
    public DemoVo queryDemo(Long id) {
        if(id==0){
            throw new BusinessException(UserServiceResultCode.PARAM_ERROR);
        }

        return demoCache.getDemoVo(id);
    }
}
