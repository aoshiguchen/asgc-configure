package com.github.aoshiguchen.framework.configure;

import org.junit.Test;

import com.github.aoshiguchen.framework.configure.ConfigReaderFactory;
import com.github.aoshiguchen.framework.configure.Configure;

public class ConfigReaderFactoryTest {

	@Test
	public void test1() {

		Configure configure = ConfigReaderFactory.getConfigure("config.properties", "properties");

		System.out.println(configure);

	}

	@Test
	public void test2(){
		
		Configure configure = ConfigReaderFactory.getConfigure("config.properties");
		
		System.out.println(configure);
		
	}
	
}
