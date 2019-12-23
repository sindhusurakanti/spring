package org.brightlife.api.service.model.dto.request.sponsor;

public class SponsorStudentRequest extends SponsorProfile {

	private Long studentId;

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
}
