package com.asgc.framework.configure.ex;

public class IniConfigReaderException  extends ConfigReaderException{

	private static final long serialVersionUID = 1L;
	
	public IniConfigReaderException() {
		super();
	}
	
	public IniConfigReaderException(String content) {
		super(content);
	}

}
