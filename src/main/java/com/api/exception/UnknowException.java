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

	@Override
	protected HttpStatus getStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
