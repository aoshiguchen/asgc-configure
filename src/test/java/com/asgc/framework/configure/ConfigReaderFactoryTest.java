package com.asgc.framework.configure;

import org.junit.Test;

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
