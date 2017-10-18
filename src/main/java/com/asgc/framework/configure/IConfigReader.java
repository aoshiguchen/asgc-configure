package com.asgc.framework.configure;

import com.asgc.framework.configure.ex.ConfigReaderException;

/**
 * 读取配置接口
 * @author aoshiguchen
 * @time 2015-08-20	
 */
public interface IConfigReader {
	
	//读取所有配置
	public Configure readAll(String path) throws ConfigReaderException;
		
}
