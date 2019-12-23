package org.brightlife.api.service.model.dto.response;

import java.util.List;

public class SponsoredStudentsResponse extends ResponseData {

	private List<SponsoredStudentsList> responseList;

	public List<SponsoredStudentsList> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<SponsoredStudentsList> responseList) {
		this.responseList = responseList;
	}
}
