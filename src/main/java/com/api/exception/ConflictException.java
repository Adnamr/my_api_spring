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

	@Override
	protected HttpStatus getStatus() {
		return HttpStatus.CONFLICT;
	}

}
