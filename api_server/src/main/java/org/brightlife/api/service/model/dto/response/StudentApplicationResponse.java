package org.brightlife.api.service.model.dto.response;

import java.util.List;

import org.brightlife.api.service.model.ApplicantDetails;
import org.brightlife.api.service.model.ApplicantDocuments;
import org.brightlife.api.service.model.ApplicantFundingDetails;
import org.brightlife.api.service.model.StudentApplication;
import org.brightlife.api.service.model.StudentGuardianDetails;
import org.brightlife.api.service.model.StudentOrphanageDetails;
import org.brightlife.api.service.model.StudentParentDetails;
import org.brightlife.api.service.model.StudentProfile;

public class StudentApplicationResponse extends ResponseData {

	private long applicationId;
	private StudentProfile student;
	private StudentApplication application;
	private StudentGuardianDetails guardianDetails;
	private StudentOrphanageDetails orphanageDetails;
	private StudentParentDetails parentDetails;
	private List<ApplicantDocuments> documents;
	private ApplicantDetails applicantDetails;
	private ApplicantFundingDetails fundingDetails;

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public StudentApplication getStudentApplication() {
		return application;
	}

	public void setStudentApplication(StudentApplication studentApplication) {
		this.application = studentApplication;
	}

	public StudentProfile getStudent() {
		return student;
	}

	public void setStudent(StudentProfile student) {
		this.student = student;
	}

	public StudentGuardianDetails getGuardianDetails() {
		return guardianDetails;
	}

	public void setGuardianDetails(StudentGuardianDetails studentGuardianDetails) {
		this.guardianDetails = studentGuardianDetails;
	}

	public StudentOrphanageDetails getOrphanageDetails() {
		return orphanageDetails;
	}

	public void setOrphanageDetails(StudentOrphanageDetails orphanageDetails) {
		this.orphanageDetails = orphanageDetails;
	}

	public StudentParentDetails getParentDetails() {
		return parentDetails;
	}

	public void setParentDetails(StudentParentDetails parentDetails) {
		this.parentDetails = parentDetails;
	}

	public ApplicantDetails getApplicantDetails() {
		return applicantDetails;
	}

	public void setApplicantDetails(ApplicantDetails applicantDetails) {
		this.applicantDetails = applicantDetails;
	}

	public ApplicantFundingDetails getFundingDetails() {
		return fundingDetails;
	}

	public void setFundingDetails(ApplicantFundingDetails fundingDetails) {
		this.fundingDetails = fundingDetails;
	}

	public List<ApplicantDocuments> getDocuments() {
		return documents;
	}

	public void setDocuments(List<ApplicantDocuments> documents) {
		this.documents = documents;
	}

}
