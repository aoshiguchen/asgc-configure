package com.github.aoshiguchen.framework.configure.impl;

import com.github.aoshiguchen.framework.configure.Configure;
import com.github.aoshiguchen.framework.configure.IConfigReader;
import com.github.aoshiguchen.framework.configure.ex.ConfigReaderException;
import com.github.aoshiguchen.framework.configure.ex.IniConfigReaderException;

/**
 * 读取txt配置实现
 * @author aoshiguchen
 */

public class TxtConfigReaderImpl implements IConfigReader {
	
	public Configure readAll(String path) throws ConfigReaderException {
		
		throw new IniConfigReaderException();
	}

}
