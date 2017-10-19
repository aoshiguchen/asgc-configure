package com.github.aoshiguchen.framework.configure.ex;

public class TxtConfigReaderException extends ConfigReaderException {

	private static final long serialVersionUID = 1L;
	
	public TxtConfigReaderException() {
		super();
	}
	
	public TxtConfigReaderException(String content) {
		super(content);
	}
}
