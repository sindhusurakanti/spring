package org.brightlife.api.service.model.dto.response;

public class ValidationError {
	String fieldName;
	String errorMessage;
	String errorType;
	public ValidationError(String fieldName, String errorMessage, String errorType) {
		super();
		this.fieldName = fieldName;
		this.errorMessage = errorMessage;
		this.errorType = errorType;
	}
	
	

}
