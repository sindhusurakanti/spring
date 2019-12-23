package org.brightlife.api.service.model.dto.response;

import java.util.List;

import org.brightlife.api.service.model.ApplicantDetails;
import org.brightlife.api.service.model.ApplicantFundingDetails;
import org.brightlife.api.service.model.SponsorProfileModel;
import org.brightlife.api.service.model.StudentApplication;
import org.brightlife.api.service.model.StudentGuardianDetails;
import org.brightlife.api.service.model.StudentIncomeDetails;
import org.brightlife.api.service.model.StudentOrphanageDetails;
import org.brightlife.api.service.model.StudentParentDetails;
import org.brightlife.api.service.model.StudentProfile;

public class StudentSummaryResponse {

	private ApplicantFundingDetails fundingDetails;
	private ApplicantDetails applicantDetails;
	private StudentOrphanageDetails orphanageDetails;
	private StudentParentDetails studentParentDetails;
	private StudentGuardianDetails studentGuardianDetails;
	private StudentProfile studentProfile;
	private List<StudentApplication> studentApplications;
	private List<SponsorProfileModel> sponsorProfileList;
	private StudentIncomeDetails studentIncomeDetails;

	public ApplicantFundingDetails getFundingDetails() {
		return fundingDetails;
	}

	public void setFundingDetails(ApplicantFundingDetails fundingDetails) {
		this.fundingDetails = fundingDetails;
	}

	public ApplicantDetails getApplicantDetails() {
		return applicantDetails;
	}

	public void setApplicantDetails(ApplicantDetails applicantDetails) {
		this.applicantDetails = applicantDetails;
	}

	public StudentOrphanageDetails getOrphanageDetails() {
		return orphanageDetails;
	}

	public void setOrphanageDetails(StudentOrphanageDetails orphanageDetails) {
		this.orphanageDetails = orphanageDetails;
	}

	public StudentParentDetails getStudentParentDetails() {
		return studentParentDetails;
	}

	public void setStudentParentDetails(StudentParentDetails studentParentDetails) {
		this.studentParentDetails = studentParentDetails;
	}

	public StudentGuardianDetails getStudentGuardianDetails() {
		return studentGuardianDetails;
	}

	public void setStudentGuardianDetails(StudentGuardianDetails studentGuardianDetails) {
		this.studentGuardianDetails = studentGuardianDetails;
	}

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}

	public List<StudentApplication> getStudentApplications() {
		return studentApplications;
	}

	public void setStudentApplications(List<StudentApplication> studentApplications) {
		this.studentApplications = studentApplications;
	}

	public List<SponsorProfileModel> getSponsorProfileList() {
		return sponsorProfileList;
	}

	public void setSponsorProfileList(List<SponsorProfileModel> sponsorProfileList) {
		this.sponsorProfileList = sponsorProfileList;
	}

	public StudentIncomeDetails getStudentIncomeDetails() {
		return studentIncomeDetails;
	}

	public void setStudentIncomeDetails(StudentIncomeDetails studentIncomeDetails) {
		this.studentIncomeDetails = studentIncomeDetails;
	}

}
