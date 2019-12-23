package org.brightlife.api.service.model.dto.response;

public class FileUploadResponse extends ResponseData {

	private String successMessage;
	private String fileName;

	public FileUploadResponse(String successMessage, String fileName) {
		super();
		this.successMessage = successMessage;
		this.fileName = fileName;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public String getUrl() {
		return fileName;
	}

}
