package com.asgc.framework.configure;

/**
 * String工具类
 * @author aoshiguchen
 * @time 2017-09-11
 */
public class StringUtil {
	
	/**
	 * 获取文件后缀
	 * @param s
	 * @return
	 */
	public static String getSuffix(String s){
		int index = s.lastIndexOf('.');
		
		if(-1 == index){
			return "";
		}
		
		return s.substring(index + 1).trim();
	}
	
}
