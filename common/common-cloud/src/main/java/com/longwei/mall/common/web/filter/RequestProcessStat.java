package com.longwei.mall.common.web.filter;

import lombok.Data;

import java.util.Date;

@Data
public class RequestProcessStat {
    private String url;
    private Date startTime;
    private Date endTime;
    private long costMills;
    private String code;
}
