package com.github.aoshiguchen.framework.configure;

import com.github.aoshiguchen.framework.configure.ex.ConfigReaderException;

/**
 * 读取配置接口
 * @author aoshiguchen
 */
public interface IConfigReader {
	
	//读取所有配置
	public Configure readAll(String path) throws ConfigReaderException;
		
}
