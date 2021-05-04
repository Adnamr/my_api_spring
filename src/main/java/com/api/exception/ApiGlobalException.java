package com.api.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiGlobalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiGlobalException(String message) {
		super(message);
	}
	
	protected abstract HttpStatus getStatus();
	

}
