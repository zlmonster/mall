package com.longwei.mall.common.web.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志工具类
 * @author lizhilong
 */
public class LoggerUtils {
	/**
	 * 错误日志
	 */
	public static Logger ERROR_LOGGER = LoggerFactory.getLogger("ErrorLogger");
	/**
	 * restful api接口访问埋点日志
	 */
	public static Logger RESTFUL_API_LOGGER = LoggerFactory.getLogger("RestfulApiLogger");
}
