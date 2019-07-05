package io.flash.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.flash.service.SysGeneratorService;
import io.flash.utils.PageUtils;
import io.flash.utils.Query;
import io.flash.utils.R;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author lizhilong
 * @email dragonjackielee@163.com
 * @date 2016年12月19日 下午9:12:58
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils pageUtil = sysGeneratorService.queryList(new Query(params));
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 * apiName: portal应用名称，如client.api
	 * serviceName: 微服务名称， 如user.service
	 * businessName: api 业务模块名称 user
	 */
	@RequestMapping("/code")
	public void code(String tables, HttpServletResponse response, String apiName, String serviceName, String businessName, String prefix) throws IOException{
		byte[] data = sysGeneratorService.generatorCode(tables.split(","), apiName, serviceName, businessName, prefix);
		
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"flash.zip\"");
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());  
	}
}
