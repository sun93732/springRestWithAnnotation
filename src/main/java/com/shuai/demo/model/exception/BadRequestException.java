package com.shuai.demo.model.exception;

public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String field = "unspecified";
	
	@Deprecated
	public BadRequestException() {
		super("unspecified");
	}
	
	public BadRequestException(String message) {
		super(message);
	}
	
	public BadRequestException(String field, String message) {
		super(message);
		this.field = field;
	}

	public String getField() {
		return field;
	}
}
