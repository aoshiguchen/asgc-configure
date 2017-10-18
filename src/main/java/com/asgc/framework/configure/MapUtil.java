package com.asgc.framework.configure;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Map工具类
 * @author aoshiguchen
 * @time 2017-09-12
 */
public class MapUtil {

	/**
	 * map变量处理，不支持传递、嵌套、递归
	 * @param map
	 */
	public static void mapVarProc(Map<String,String> map){
		Pattern PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");
		
		for(String key : map.keySet()){
			String val = map.get(key);
			Matcher matcher = PATTERN.matcher(val);
			while(matcher.find()){
				//需要替换的目标字符串
				String target = matcher.group(0);
				//变量名
				String var = matcher.group(1).trim();
								
				//如果map中包含该变量，则替换
				if(map.containsKey(var)){
					val = val.replace(target, map.get(var));
				}
			}
			
			map.put(key, val);
		}
	}
	
}
