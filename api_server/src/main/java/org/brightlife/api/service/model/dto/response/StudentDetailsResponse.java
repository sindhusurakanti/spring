package org.brightlife.api.service.model.dto.response;

import java.util.List;

public class StudentDetailsResponse  extends ResponseData {

	private List<StudentSummaryResponse> responseList;

	public List<StudentSummaryResponse> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<StudentSummaryResponse> responseList) {
		this.responseList = responseList;
	}
}
