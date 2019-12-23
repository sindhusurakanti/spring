package org.brightlife.api.service.model.dto.request.studentapplication;

public class StudentProfileEditRequest extends StudentApplicationRequest {

	private Long studentProfileId;

	public Long getStudentProfileId() {
		return studentProfileId;
	}

	public void setStudentProfileId(Long studentProfileId) {
		this.studentProfileId = studentProfileId;
	}

}
