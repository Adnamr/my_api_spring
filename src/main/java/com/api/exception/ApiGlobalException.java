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
	
	 public ApiGlobalException(Class<?> clazz, long id) {
	        super(String.format("Entity %s with id %d not found", clazz.getSimpleName(), id));
	 
	 }
	 
	 public ApiGlobalException(Class<?> clazz, String id) {
        super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), id));
    }

//    public ApiGlobalException(Class<?> clazz, ObjectId id) {
//        super(String.format("Entity %s with id %s not found", clazz.getSimpleName(), id.toString()));
//    }
	
	protected abstract HttpStatus getStatus();
	

}
