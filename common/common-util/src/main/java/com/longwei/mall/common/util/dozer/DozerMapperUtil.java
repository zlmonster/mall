package com.longwei.mall.common.util.dozer;

import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * DozerMapper映射转化工具类
 * @author xxx
 *
 */
@Component
@Slf4j
public class DozerMapperUtil {
	
	@Autowired
	private DozerBeanMapper mapper;
	
	/**定义单例*/
	private static DozerMapperUtil dozerMapperUtil = null;
		
	private DozerMapperUtil(){
		
	}
	/**单例模式*/
	public static DozerMapperUtil getInstance(){
		if(dozerMapperUtil==null){
			dozerMapperUtil = new DozerMapperUtil();
		}
		return dozerMapperUtil;
	}
	
	@PostConstruct
	public void init() {
		log.info("DozerMapperUtil init...");
		dozerMapperUtil = this;
		dozerMapperUtil.mapper = this.mapper;
	}
	
	/** 
	  * 构造新的destinationClass实例对象，通过source对象中的字段内容 
	  * 映射到destinationClass实例对象中，并返回新的destinationClass实例对象。 
	  *  
	  * @param source 源数据对象 
	  * @param destinationClass 要构造新的实例对象Class 
	  */  
	  public <T> T map(Object source, Class<T> destinationClass){  
	    return mapper.map(source, destinationClass);  
	  }  
	    
	  public <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass){  
	    List<T> destinationList = new ArrayList<T>();  
	    for (Iterator<?> iterator = sourceList.iterator(); iterator.hasNext(); ){
	    	Object sourceObject = iterator.next();
	    	destinationList.add(mapper.map(sourceObject, destinationClass));  
	    }  
	    return destinationList;  
	  }  
	  
	    
	  /** 
	  * 将对象source的所有属性值拷贝到对象destination中. 
	  *  
	  * @param source 对象source 
	  * @param destinationObject 对象destination
	  */  
	  public void copy(Object source, Object destinationObject){
		  mapper.map(source, destinationObject);  
	  }  
	  
}
