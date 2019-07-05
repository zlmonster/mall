package io.flash.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.flash.dao.GeneratorDao;
import io.flash.utils.GenUtils;
import io.flash.utils.PageUtils;
import io.flash.utils.Query;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * 
 * @author lizhilong
 * @email dragonjackielee@163.com
 * @date 2016年12月19日 下午3:33:38
 */
@Service
public class SysGeneratorService {
	@Autowired
	private GeneratorDao generatorDao;

	public PageUtils queryList(Query query) {
		Page<?> page = PageHelper.startPage(query.getPage(), query.getLimit());
		List<Map<String, Object>> list = generatorDao.queryList(query);

		return new PageUtils(list, (int)page.getTotal(), query.getLimit(), query.getPage());
	}

	public Map<String, String> queryTable(String tableName) {
		return generatorDao.queryTable(tableName);
	}

	public List<Map<String, String>> queryColumns(String tableName) {
		return generatorDao.queryColumns(tableName);
	}

	public byte[] generatorCode(String[] tableNames, String apiName, String serviceName, String businessName, String prefix) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);

			List<Map<String, String>> columnsMap = null;
			Map<String, String> paramMap = null;

			//生成代码
			GenUtils.generatorCode(table, columns, zip, apiName, serviceName, businessName, paramMap, prefix);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

	private static Map<String, String> abcMap = new HashMap<>();


	public static void initMap (){
		abcMap.put("1","a");
		abcMap.put("2","b");
		abcMap.put("3","c");
		abcMap.put("4","d");
		abcMap.put("5","e");
		abcMap.put("6","f");
		abcMap.put("7","g");
		abcMap.put("8","h");
		abcMap.put("9","i");
		abcMap.put("10","j");
		abcMap.put("11","k");
		abcMap.put("12","L");
		abcMap.put("13","m");
		abcMap.put("14","n");
		abcMap.put("15","O");
		abcMap.put("16","P");
		abcMap.put("17","Q");
		abcMap.put("18","R");
		abcMap.put("19","S");
		abcMap.put("20","T");
		abcMap.put("21","U");
		abcMap.put("22","V");
		abcMap.put("23","W");
		abcMap.put("24","X");
		abcMap.put("25","Y");
		abcMap.put("26","Z");
	}


	public static String getResult(String str, String result){

		if(str.length() == 1){
			result += abcMap.get(str);
			System.out.println(result);
			return result;
		} else if (str.length() == 0){
			System.out.println(result);
			return result;
		}else {

			String b = str.substring(0,2);
			if(Integer.parseInt(b) > 26){
				String a = str.substring(0,1);
				String aa = str.substring(1);
				result += abcMap.get(a);
				getResult(aa, result);
			} else {
				String result1 = result;
				String result2 = result;
				for(int i = 0; i < 2; i++){
					if(i == 0){
						String bb = str.substring(2);
						result1 += abcMap.get(b);
						getResult(bb, result1);
					} else {
						String a = str.substring(0,1);
						String aa = str.substring(1);
						result2 += abcMap.get(a);
						getResult(aa, result2);
					}

				}
			}
		}
//		System.out.println(result);
		return result;
	}

	public static void main(String[] args) {
		initMap();
		String abc = "3212313";
		String result="";
		getResult(abc, result);
	}
}
