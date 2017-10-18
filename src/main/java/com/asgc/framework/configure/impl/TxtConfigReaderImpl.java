package com.asgc.framework.configure.impl;

import com.asgc.framework.configure.Configure;
import com.asgc.framework.configure.IConfigReader;
import com.asgc.framework.configure.ex.ConfigReaderException;
import com.asgc.framework.configure.ex.IniConfigReaderException;

/**
 * 读取txt配置实现
 * @author aoshiguchen
 * @time 2015-08-20	
 */

public class TxtConfigReaderImpl implements IConfigReader {
	
	public Configure readAll(String path) throws ConfigReaderException {
		
		throw new IniConfigReaderException();
	}

}
