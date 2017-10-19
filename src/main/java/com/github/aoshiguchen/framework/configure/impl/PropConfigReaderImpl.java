package com.github.aoshiguchen.framework.configure.impl;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.github.aoshiguchen.framework.configure.Configure;
import com.github.aoshiguchen.framework.configure.IConfigReader;
import com.github.aoshiguchen.framework.configure.ex.ConfigReaderException;
import com.github.aoshiguchen.framework.configure.ex.PropConfigReaderException;

/**
 * 读取properties配置实现
 * @author aoshiguchen
 */
public class PropConfigReaderImpl implements IConfigReader {
	
	public Configure readAll(String path) throws ConfigReaderException {
		
		Map<String,String> config = new HashMap<String, String>();
		
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(path));
			
			for(Object key : prop.keySet()){
				config.put((String)key,(String)prop.getProperty((String)key));
			}
			
		} catch (Exception e) {
			throw new PropConfigReaderException("config file not found or format error !");
		} 
		
		return new Configure(config);
	}

}