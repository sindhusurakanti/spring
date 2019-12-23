package org.brightlife.api.service.model.dto.response;

import java.util.List;

public class GuardianAppliedStudentsResponse extends ResponseData{

	private List<AppliedStudents> responseList;

	public List<AppliedStudents> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<AppliedStudents> responseList) {
		this.responseList = responseList;
	}

}
