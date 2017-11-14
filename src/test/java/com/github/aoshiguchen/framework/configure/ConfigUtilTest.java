package com.github.aoshiguchen.framework.configure;

import org.junit.Test;

import com.github.aoshiguchen.framework.configure.annotation.Config;

public class ConfigUtilTest {
	
	@Config
	private int a;
	@Config
	private String b;
	
	@Test
	public void test1(){
		Configure configure = ConfigReaderFactory.getConfigure("config.properties");
		
		ConfigUtil.inject(configure, this);
		
		System.out.println(a);
		System.out.println(b);
	}
	
}
