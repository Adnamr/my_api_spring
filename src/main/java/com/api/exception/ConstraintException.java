package com.api.exception;

import org.springframework.http.HttpStatus;

public class ConstraintException extends ApiGlobalException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConstraintException(String message) {
		super(message);
	}

	@Override
	protected HttpStatus getStatus() {
		return HttpStatus.BAD_REQUEST;
	}

}
