package org.brightlife.api.service.model.dto.request.studentapplication;

public class StudentSummaryRequest extends StudentApplicationRequest {

	private String applicationId;
	private String studentId;

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public StudentSummaryRequest(String applicationId, String studentId) {
		super();
		this.applicationId = applicationId;
		this.studentId = studentId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
