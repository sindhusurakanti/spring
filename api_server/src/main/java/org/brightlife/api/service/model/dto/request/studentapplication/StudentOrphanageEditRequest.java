package org.brightlife.api.service.model.dto.request.studentapplication;

public class StudentOrphanageEditRequest extends StudentApplicationRequest {

	private Long studentApplicationId;

	public Long getStudentApplicationId() {
		return studentApplicationId;
	}

	public void setStudentApplicationId(Long studentApplicationId) {
		this.studentApplicationId = studentApplicationId;
	}

}
