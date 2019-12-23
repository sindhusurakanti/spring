package org.brightlife.api.service.model.dto.response;

import java.util.List;

public class SimpleResponse extends ResponseData {
	private String message = "";
	private List<String> messagesList = null;

	public SimpleResponse(String message, List<String> messagesList) {
		super();
		this.message = message;
		this.messagesList = messagesList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getMessagesList() {
		return messagesList;
	}

	public void setMessagesList(List<String> messagesList) {
		this.messagesList = messagesList;
	}

}
