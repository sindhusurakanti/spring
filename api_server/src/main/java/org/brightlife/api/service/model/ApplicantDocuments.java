package org.brightlife.api.service.model;

import java.time.LocalDateTime;

public class ApplicantDocuments {

	private Long id;
	private Long applicationId;
	private String documnetCode;
	private String documnetImageURL;
	private boolean isVerified;
	private String verifiedBy;
	private LocalDateTime verificationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getDocumnetCode() {
		return documnetCode;
	}

	public void setDocumnetCode(String documnetCode) {
		this.documnetCode = documnetCode;
	}

	public String getDocumnetImageURL() {
		return documnetImageURL;
	}

	public void setDocumnetImageURL(String documnetImageURL) {
		this.documnetImageURL = documnetImageURL;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getVerifiedBy() {
		return verifiedBy;
	}

	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}

	public LocalDateTime getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(LocalDateTime verificationDate) {
		this.verificationDate = verificationDate;
	}

}
