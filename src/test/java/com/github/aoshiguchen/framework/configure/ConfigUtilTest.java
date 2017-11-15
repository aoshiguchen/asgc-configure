package com.github.aoshiguchen.framework.configure;

import org.junit.Test;

import com.github.aoshiguchen.framework.configure.annotation.Config;

public class ConfigUtilTest {
	
	@Config
	private String projectName;
	
	@Config("jdbc.port")
	private int port;
	
	@Config("jdbc.url")
	private String url;
	
	{
		/**
		 * 与spring整合时，可以将这行代码置入spring前置处理器
		 * sprign容器初始化时就会自动注入
		 */
		ConfigUtil.inject("config", this);
	}
	
	//注入式读取配置
	@Test
	public void test1(){
		System.out.println(projectName);
		System.out.println(port);
		System.out.println(url);
	}
	
	//直接读取
	@Test
	public void test2(){
		Configure configure = ConfigReaderFactory.getConfigure("config");
		
		System.out.println(configure.getString("jdbc.host"));
		System.out.println(configure.getString("jdbc.driver_class"));
		System.out.println(configure.getString("jdbc.username"));
	}
	
}
