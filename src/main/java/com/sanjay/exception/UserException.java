package com.sanjay.exception;

public class UserException extends RuntimeException {
	private String message;
	public UserException( ) {
		super();

	}

	public UserException(String message, int code) {
		this.message = message;
	}

	public UserException(String message) {
		this.message = message;
	}

	public UserException(String message, String... args) {
		this.message = message;
		for (String arg : args) {
			this.message = this.message.replaceFirst("\\{}", arg);
		}
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
