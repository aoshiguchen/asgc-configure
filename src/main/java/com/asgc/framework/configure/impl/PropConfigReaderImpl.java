package com.asgc.framework.configure.impl;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.asgc.framework.configure.Configure;
import com.asgc.framework.configure.IConfigReader;
import com.asgc.framework.configure.ex.ConfigReaderException;
import com.asgc.framework.configure.ex.PropConfigReaderException;

/**
 * 读取properties配置实现
 * @author aoshiguchen
 * @time 2015-08-20	
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