package org.brightlife.api.service.model.dto.request.studentapplication;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadRequest extends StudentApplicationRequest {
	
	private MultipartFile file;

	
	public FileUploadRequest(MultipartFile file) {
		super();
		this.file = file;
	}

	public MultipartFile getFile() {
		return file;
	}

}
