package org.brightlife.api.service.model.dto.response;

public class SignUpResponse extends ResponseData{
	int nextAction;
	String message;
	
	public int getNextAction() {
		return nextAction;
	}
	public void setNextAction(int nextAction) {
		this.nextAction = nextAction;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
