package com.asgc.framework.configure;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ConfigureTest {

//	@Test
//	public void test1(){
//		Configure configure = new Configure(null);
//	}
	
	@Test
	public void test2(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name","zhangsan");
		map.put("age", "20");
				
		Configure configure = new Configure(map);
		configure.set("sex", "男");
		configure.set("height","173");
		
		System.out.println(configure.get("name"));
		System.out.println(configure.getInt("age"));
		System.out.println(configure.getString("sex"));
		System.out.println(configure.getLong("height"));
		
	}
	
}
