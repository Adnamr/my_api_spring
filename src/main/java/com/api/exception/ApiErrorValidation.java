package com.api.exception;

import java.util.ArrayList;
import java.util.List;

public class ApiErrorValidation {
	
	private List<String> errors;

	
	public ApiErrorValidation() {
		super();
	}
	
	public ApiErrorValidation(List<String> errors) {
		super();
		this.errors = errors;
	}
	
	public List<String> addError(String error)
	{
		
		errors = new ArrayList<String>();
		errors.add(error);
		return errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	

}
