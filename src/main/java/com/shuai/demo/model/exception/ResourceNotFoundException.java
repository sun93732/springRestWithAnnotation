package com.shuai.demo.model.exception;

public class ResourceNotFoundException extends RuntimeException {
	private String message;

	private static final long serialVersionUID = -4964481136742603985L;
	public ResourceNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
