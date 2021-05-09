package com.api.exception;

import org.springframework.http.HttpStatus;

public class UnknowException extends ApiGlobalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnknowException(String message) {
		super(message);
	}
	
	public UnknowException(Class<?> clazz, long id) {
        super(String.format("Entity %s with id %d not found", clazz.getSimpleName(), id));
	}
 
	public UnknowException(Class<?> clazz, String id) {
	    super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), id));
	}

	@Override
	protected HttpStatus getStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
