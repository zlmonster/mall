package com.longwei.mall.oms.business.demo.service;

import com.longwei.mall.oms.business.demo.vo.DemoVo;

/**
 * @author lizhilong
 */
public interface IDemoService {
    /**
     * 查询demo
     * @param id
     * @return
     */
    DemoVo queryDemo(Long id);
    /**
     * 查询demo
     * @param id
     * @return
     */
    DemoVo queryDemo1(Long id);

    void deleteDemo(Long id);

    DemoVo insertOrUpdateDemo(Long id);
}
