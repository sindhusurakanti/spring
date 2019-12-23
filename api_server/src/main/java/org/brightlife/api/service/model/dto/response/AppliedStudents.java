package org.brightlife.api.service.model.dto.response;

import org.brightlife.api.service.model.ApplicantDetails;
import org.brightlife.api.service.model.ApplicantFundingDetails;
import org.brightlife.api.service.model.StudentApplication;
import org.brightlife.api.service.model.StudentGuardianDetails;
import org.brightlife.api.service.model.StudentIncomeDetails;
import org.brightlife.api.service.model.StudentOrphanageDetails;
import org.brightlife.api.service.model.StudentParentDetails;
import org.brightlife.api.service.model.StudentProfile;

public class AppliedStudents {

	private StudentProfile studentProfile;

	private StudentApplication studentApplicationDetails;

	private ApplicantFundingDetails fundingDetails;

	private StudentGuardianDetails guardianDetails;

	private StudentOrphanageDetails orphanageDetails;

	private StudentParentDetails parentDetails;

	private ApplicantDetails applicantDetails;

	private StudentIncomeDetails studentIncomeDetails;

	public StudentProfile getProfile() {
		return studentProfile;
	}

	public void setProfile(StudentProfile profile) {
		this.studentProfile = profile;
	}

	public ApplicantFundingDetails getFundingDetails() {
		return fundingDetails;
	}

	public void setFundingDetails(ApplicantFundingDetails fundingDetails) {
		this.fundingDetails = fundingDetails;
	}

	public StudentGuardianDetails getGuardianDetails() {
		return guardianDetails;
	}

	public void setGuardianDetails(StudentGuardianDetails guardianDetails) {
		this.guardianDetails = guardianDetails;
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

	public StudentApplication getStudentApplicationDetails() {
		return studentApplicationDetails;
	}

	public void setStudentApplicationDetails(StudentApplication studentApplicationDetails) {
		this.studentApplicationDetails = studentApplicationDetails;
	}

	public StudentIncomeDetails getStudentIncomeDetails() {
		return studentIncomeDetails;
	}

	public void setStudentIncomeDetails(StudentIncomeDetails studentIncomeDetails) {
		this.studentIncomeDetails = studentIncomeDetails;
	}

}
