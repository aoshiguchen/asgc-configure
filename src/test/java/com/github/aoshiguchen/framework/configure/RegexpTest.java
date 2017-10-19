package com.github.aoshiguchen.framework.configure;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexpTest {
	
	@Test
	public void test1(){
		Pattern PATTERN = Pattern.compile("\\$\\{([^\\}]+)\\}");
		Matcher matcher1 = PATTERN.matcher("121${${a}213${b}${ c}  ${ d } aaa ${x}");
		
		while(matcher1.find()){
			System.out.println(matcher1.group(0));
			System.out.println(matcher1.group(1));
		}
	}
	
	@Test
	public void test2(){
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("a","aa");
		map.put("b","bb");
		map.put("c","${ a}23");
		map.put("d","${ a }/${b}");
		
		System.out.println(map);
		mapVarProc(map);
		System.out.println(map);
	}
	
	@Test
	public void test3(){
		Map<String,String> map = new HashMap<String, String>();
		
		map.put("a","aa");
		map.put("b","bb");
		map.put("c","${ a}23");
		map.put("d","${ c}/zz");
		map.put("e","${f}...");
		map.put("f","1${a}2 ");
		map.put("g","${g}1 ");
		
		System.out.println(map);
		mapVarProc(map);
		System.out.println(map);
	}
	
	/**
	 * map变量处理，不支持传递、嵌套、递归
	 * @param map
	 */
	public void mapVarProc(Map<String,String> map){
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
