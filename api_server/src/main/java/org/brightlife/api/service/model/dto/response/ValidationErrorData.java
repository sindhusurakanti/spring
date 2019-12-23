package org.brightlife.api.service.model.dto.response;

import java.util.ArrayList;
import java.util.List;

import org.brightlife.api.service.consts.ErrorConstants;

public class ValidationErrorData extends ErrorData {

	List<ValidationError> errors = new ArrayList<ValidationError>();
	
	public ValidationErrorData() {
		super(ErrorConstants.VALIDATION_ERROR, "Input validation failed");
	}
	
	public void addError(ValidationError err) {
		errors.add(err);
	}
	
	public boolean hasErrors(){
		return errors.size() > 0;
	}
	
}
