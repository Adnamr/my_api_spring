package com.api.exception;

import org.springframework.http.HttpStatus;

public class NotExistException extends ApiGlobalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public NotExistException(String message) {
		super(message);
	}
	
	public NotExistException(Class<?> clazz, long id) {
        super(String.format("Entity %s with id %d not found", clazz.getSimpleName(), id));
	}
 
	public NotExistException(Class<?> clazz, String id) {
	    super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), id));
	}

	@Override
	protected HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
