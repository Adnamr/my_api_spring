package com.api.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiGlobalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConflictException(String message) {
		super(message);
	}
	
	public ConflictException(Class<?> clazz, long id) {
        super(String.format("Entity %s with id %d not found", clazz.getSimpleName(), id));
	}
 
	public ConflictException(Class<?> clazz, String id) {
	    super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), id));
	}

	@Override
	protected HttpStatus getStatus() {
		return HttpStatus.CONFLICT;
	}

}
