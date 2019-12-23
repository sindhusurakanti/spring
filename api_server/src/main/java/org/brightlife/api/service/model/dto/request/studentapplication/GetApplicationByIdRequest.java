package org.brightlife.api.service.model.dto.request.studentapplication;

public class GetApplicationByIdRequest extends StudentApplicationRequest{

	private String id;

	public GetApplicationByIdRequest(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
