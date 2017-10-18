package com.asgc.framework.configure.ex;

public class JsonConfigReaderException extends ConfigReaderException{

	private static final long serialVersionUID = 1L;
	
	public JsonConfigReaderException() {
		super();
	}
	
	public JsonConfigReaderException(String content) {
		super(content);
	}

}

