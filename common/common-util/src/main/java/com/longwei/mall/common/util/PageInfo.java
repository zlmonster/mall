package com.longwei.mall.common.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageInfo<T> implements Serializable {

    //结果集
    private List<T> list;

    //总记录数
    private long total;

    public PageInfo(){}
    /**
     * 包装Page对象
     *
     * @param list
     */
    public PageInfo(List<T> list) {
        this.list = list;
    }
}
