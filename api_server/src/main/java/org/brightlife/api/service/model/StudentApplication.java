package org.brightlife.api.service.model;

import java.time.LocalDateTime;

import org.brightlife.api.service.model.dto.response.ResponseData;

public class StudentApplication extends ResponseData {

	private Long id;
	private Long studentId;
	private String status;
	private String approvedBy;
	private LocalDateTime approvedDate;
	private Long appliedBy;
	private LocalDateTime appliedDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDateTime getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(LocalDateTime approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Long getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(Long appliedBy) {
		this.appliedBy = appliedBy;
	}

	public LocalDateTime getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(LocalDateTime appliedDate) {
		this.appliedDate = appliedDate;
	}

}
