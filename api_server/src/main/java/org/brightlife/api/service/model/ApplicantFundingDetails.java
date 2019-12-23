package org.brightlife.api.service.model;

import org.brightlife.api.service.model.dto.response.ResponseData;

public class ApplicantFundingDetails extends ResponseData{

	private long id;
	private long amount;
	private long expensesAmount;
	private long schoolNeedsAmount;
	private long physicalHealthAmount;
	private String duration;
	private String purpose;
	private String purposeDescription;
	private String currencyCode;
	private long applicationId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getExpensesAmount() {
		return expensesAmount;
	}

	public void setExpensesAmount(long expensesAmount) {
		this.expensesAmount = expensesAmount;
	}

	public long getSchoolNeedsAmount() {
		return schoolNeedsAmount;
	}

	public void setSchoolNeedsAmount(long schoolNeedsAmount) {
		this.schoolNeedsAmount = schoolNeedsAmount;
	}

	public long getPhysicalHealthAmount() {
		return physicalHealthAmount;
	}

	public void setPhysicalHealthAmount(long physicalHealthAmount) {
		this.physicalHealthAmount = physicalHealthAmount;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPurposeDescription() {
		return purposeDescription;
	}

	public void setPurposeDescription(String purposeDescription) {
		this.purposeDescription = purposeDescription;
	}

}
