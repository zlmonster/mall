/**
 * Copyright 2018 闪电开源 http://www.flash.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.flash.utils;

import io.flash.entity.ColumnEntity;
import io.flash.entity.TableEntity;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 * 
 * @author lizhilong
 * @email dragonjackielee@163.com
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {


	public static List<String> getTemplates(){
		List<String> templates = new ArrayList<String>();

		//加载facade模块
		templates.add("template/center_api/ListRequest.java.vm");
		templates.add("template/center_api/ListResponse.java.vm");
		templates.add("template/center_api/GetRequest.java.vm");
		templates.add("template/center_api/CreateRequest.java.vm");
		templates.add("template/center_api/DeleteRequest.java.vm");
		templates.add("template/center_api/UpdateRequest.java.vm");
		templates.add("template/center_api/GetResponse.java.vm");
		templates.add("template/center_api/Mapping.java.vm");
		templates.add("template/center_api/RibbonClient.java.vm");
		templates.add("template/center_api/Vo.java.vm");

		//加载api模块
		templates.add("template/portal_api/ApiController.java.vm");
		templates.add("template/portal_api/IApiService.java.vm");
		templates.add("template/portal_api/ApiServiceImpl.java.vm");

		//加载service模块
		templates.add("template/center_service/Controller.java.vm");
		templates.add("template/center_service/IService.java.vm");
		templates.add("template/center_service/ServiceImpl.java.vm");



		return templates;
	}
	
	/**
	 * 生成代码
	 */
	public static void generatorCode(Map<String, String> table,
			List<Map<String, String>> columns, ZipOutputStream zip, String apiName, String serviceName,String businessName, Map<String, String> columnsMap, String prefix){
		//配置信息
		Configuration config = getConfig();
		boolean hasBigDecimal = false;
		boolean hasGmtCreate = false;
		boolean hasGmtModified = false;
		boolean hasIsDeleted = false;
		//表信息
		TableEntity tableEntity = new TableEntity();
		tableEntity.setTableName(table.get("tableName"));
		tableEntity.setComments(table.get("tableComment"));
		//表名转换成Java类名
//		String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
		String className = tableToJava(tableEntity.getTableName(), prefix);
		//判断是否需要转换类名
		if(columnsMap != null && columnsMap.containsKey(tableEntity.getTableName())){
			className = columnsMap.get(tableEntity.getTableName());
		}
		tableEntity.setClassName(className);
		tableEntity.setClassname(StringUtils.uncapitalize(className));

		tableEntity.setClassNAME(StringUtils.upperCase(className));
		
		//列信息
		List<ColumnEntity> columsList = new ArrayList<>();
		for(Map<String, String> column : columns){
			ColumnEntity columnEntity = new ColumnEntity();
			columnEntity.setColumnName(column.get("columnName"));
			columnEntity.setDataType(column.get("dataType"));
			columnEntity.setJDBCTYPE(StringUtils.upperCase(column.get("dataType")).replace("ENUM","CHAR").replace("DATETIME","TIMESTAMP"));
			if(columnEntity.getJDBCTYPE().equals("INT")){
				columnEntity.setJDBCTYPE("INTEGER");
			}
			columnEntity.setComments(column.get("columnComment"));
			columnEntity.setExtra(column.get("extra"));
			String attrName = "";
			if(columnsMap != null && columnsMap.containsKey(columnEntity.getColumnName())){
				//获取转换表数据
				attrName = columnsMap.get(columnEntity.getColumnName());
			} else {
				//列名转换成Java属性名
				attrName = columnToJava(columnEntity.getColumnName());
			}

			columnEntity.setAttrName(attrName);
			columnEntity.setAttrname(StringUtils.uncapitalize(attrName));
			
			//列的数据类型，转换成Java类型
			String attrType = config.getString(columnEntity.getDataType(), "unknowType");
			columnEntity.setAttrType(attrType);
			if (!hasBigDecimal && attrType.equals("BigDecimal" )) {
				hasBigDecimal = true;
			}
			//是否主键
			if("PRI".equalsIgnoreCase(column.get("columnKey")) && tableEntity.getPk() == null){
				tableEntity.setPk(columnEntity);
			}

			if(attrName.equalsIgnoreCase("gmtCreate")){
				hasGmtCreate = true;
			}

			if(attrName.equalsIgnoreCase("gmtModified")){
				hasGmtModified = true;
			}

			if(attrName.equalsIgnoreCase("isDeleted")){
				hasIsDeleted = true;
			}

			columsList.add(columnEntity);
		}
		tableEntity.setColumns(columsList);
		
		//没主键，则第一个字段为主键
		if(tableEntity.getPk() == null){
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}
		
		//设置velocity资源加载器
		Properties prop = new Properties();  
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");  
		Velocity.init(prop);

		String mainPath = config.getString("mainPath" );
		mainPath = StringUtils.isBlank(mainPath) ? "io.flash" : mainPath;
		
		//封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableEntity.getTableName());
		map.put("comments", tableEntity.getComments());
		map.put("pk", tableEntity.getPk());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		map.put("classNAME", tableEntity.getClassNAME());
		map.put("pathName", tableEntity.getClassname().toLowerCase());
		map.put("columns", tableEntity.getColumns());
		map.put("hasBigDecimal", hasBigDecimal);
		map.put("hasGmtCreate", hasGmtCreate);
		map.put("hasGmtMotified", hasGmtModified);
		map.put("hasIsDeleted", hasIsDeleted);
		map.put("mainPath", mainPath);
		map.put("projectName", config.getString("projectName" ));
		map.put("package", config.getString("package" ));
//		map.put("moduleName", config.getString("moduleName" ));
		map.put("apiname", apiName.split("\\.")[0]);
//		map.put("apiName", columnToJava(apiName));
		map.put("servicename", serviceName.split("\\.")[0]);
		map.put("businessName",businessName);
		map.put("serviceName", columnToJava(serviceName.split("\\.")[0]));
//		map.put("facadename", serviceName);
		map.put("servicePre", tableToJava(serviceName.split("\\.")[0],null));
//		map.put("servicepre", serviceName);
        VelocityContext context = new VelocityContext(map);
        
        //获取模板列表
		List<String> templates = getTemplates();
		for(String template : templates){
			//渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			
			try {
				//添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"), map)));
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				throw new RRException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
			}
		}
	}
	
	
	/**
	 * 列名转换成Java属性名
	 */
	public static String columnToJava(String columnName) {
		return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
	}
	
	/**
	 * 表名转换成Java类名
	 */
	public static String tableToJava(String tableName, String tablePrefix) {
		if(StringUtils.isNotBlank(tablePrefix)){
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName);
	}
	
	/**
	 * 获取配置信息
	 */
	public static Configuration getConfig(){
		try {
			return new PropertiesConfiguration("generator.properties");
		} catch (ConfigurationException e) {
			throw new RRException("获取配置文件失败，", e);
		}
	}

	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, String className, String packageName, Map<String, Object> map) {
		//门户api路径
		String portalApiPackagePath = map.get("projectName")
				+ File.separator + "api"
				+ File.separator + map.get("apiname") + ".api"
				+ File.separator + "src" + File.separator + "main" + File.separator + "java"
				+ File.separator + new String(map.get("package")+"").replace(".", File.separator)
				+ File.separator + map.get("apiname")
				+ File.separator + "business"
				+ File.separator + map.get("businessName")
				+ File.separator;
		//微服务api路径
		String centerApiPackagePath =  map.get("projectName")
				+ File.separator + "microservice"
				+ File.separator + map.get("servicename") + ".center"
				+ File.separator + map.get("servicename")  +".api"
				+ File.separator + "src" + File.separator + "main" + File.separator + "java"
				+ File.separator + new String(map.get("package")+"").replace(".", File.separator)
				+ File.separator + map.get("servicename")
				+ File.separator + "api"
				+ File.separator;
		//微服务service路径
		String centerServicePackagePath =  map.get("projectName")
				+ File.separator + "microservice"
				+ File.separator + map.get("servicename") + ".center"
				+ File.separator + map.get("servicename") + ".service"
				+ File.separator + "src" + File.separator + "main" + File.separator + "java"
				+ File.separator + new String(map.get("package")+"").replace(".", File.separator)
				+ File.separator + map.get("servicename")
				+ File.separator;


		// portal api
		if (template.contains("template/portal_api/ApiController.java.vm")) {
			return portalApiPackagePath + "controller" + File.separator +"Api"+ className + "Controller.java";
		}

		if (template.contains("template/portal_api/IApiService.java.vm")) {
			return portalApiPackagePath + "service" + File.separator + "IApi" + className + "Service.java";
		}

		if (template.contains("template/portal_api/ApiServiceImpl.java.vm")) {
			return portalApiPackagePath + "service" + File.separator + "impl" + File.separator + "Api"+ className + "ServiceImpl.java";
		}


		// center api
		if (template.contains("template/center_api/ListRequest.java.vm")) {
			return centerApiPackagePath + "dto" + File.separator + "request" + File.separator + "Select" + className + "ListRequest.java";
		}

		if (template.contains("template/center_api/ListResponse.java.vm")) {
			return centerApiPackagePath + "dto" + File.separator + "response" + File.separator + "Select" + className + "ListResponse.java";
		}

		if (template.contains("template/center_api/CreateRequest.java.vm")) {
			return centerApiPackagePath + "dto" + File.separator + "request" + File.separator + "Create" + className + "Request.java";
		}

		if (template.contains("template/center_api/DeleteRequest.java.vm")) {
			return centerApiPackagePath + "dto" + File.separator + "request" + File.separator + "Delete" + className + "Request.java";
		}

		if (template.contains("template/center_api/GetRequest.java.vm")) {
			return centerApiPackagePath + "dto" + File.separator + "request" + File.separator + "Get" + className + "Request.java";
		}

		if (template.contains("template/center_api/GetResponse.java.vm")) {
			return centerApiPackagePath + "dto" + File.separator + "response" + File.separator + "Get" + className + "Response.java";
		}

		if (template.contains("template/center_api/UpdateRequest.java.vm")) {
			return centerApiPackagePath + "dto" + File.separator + "request" + File.separator + "Update" + className + "Request.java";
		}

		if (template.contains("template/center_api/Mapping.java.vm")) {
			return centerApiPackagePath + "client"  + File.separator+ "mapping"  + File.separator  + className + "Mapping.java";
		}

		if (template.contains("template/center_api/RibbonClient.java.vm")) {
			return centerApiPackagePath + "client"  + File.separator + className + "RibbonClient.java";
		}

		if (template.contains("template/center_api/Vo.java.vm")) {
			return centerApiPackagePath + "vo" + File.separator + className + "Vo.java";
		}

		//center service
		if (template.contains("template/center_service/Controller.java.vm")) {
			return centerServicePackagePath + "controller" + File.separator + className + "Controller.java";
		}

		if (template.contains("template/center_service/IService.java.vm")) {
			return centerServicePackagePath + "service" + File.separator + "I" + className + "Service.java";
		}

		if (template.contains("template/center_service/ServiceImpl.java.vm")) {
			return centerServicePackagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
		}


		return null;
	}
}
