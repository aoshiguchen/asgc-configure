package com.github.aoshiguchen.framework.configure;

/**
 * String工具类
 * @author aoshiguchen
 */
public class StringUtil {
	
	/**
	 * 获取文件后缀
	 * @param s 文件名
	 * @return 文件后缀
	 */
	public static String getSuffix(String s){
		int index = s.lastIndexOf('.');
		
		if(-1 == index){
			return "";
		}
		
		return s.substring(index + 1).trim();
	}
	
}
