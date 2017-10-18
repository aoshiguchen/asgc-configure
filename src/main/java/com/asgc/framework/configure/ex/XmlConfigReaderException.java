package com.asgc.framework.configure.ex;

public class XmlConfigReaderException extends ConfigReaderException{
	
	private static final long serialVersionUID = 1L;
	
	public XmlConfigReaderException() {
		super();
	}
	
	public XmlConfigReaderException(String content) {
		super(content);
	}
}
