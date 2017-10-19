package com.github.aoshiguchen.framework.configure.ex;

/**
 * 读取配置文件异常
 * @author aoshiguchen
 */

public class ConfigReaderException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ConfigReaderException() {
		super();
	}
	
	public ConfigReaderException(String content) {
		super(content);
	}

}