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


	@Override
	protected HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
