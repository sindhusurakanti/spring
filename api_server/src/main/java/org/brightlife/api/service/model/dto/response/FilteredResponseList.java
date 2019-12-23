package org.brightlife.api.service.model.dto.response;

import java.util.List;

public class FilteredResponseList extends ResponseData {

	private List<StudentFilteredResponse> responseList;

	public List<StudentFilteredResponse> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<StudentFilteredResponse> responseList) {
		this.responseList = responseList;
	}

}
