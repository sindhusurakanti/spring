package org.brightlife.api.service.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applicant_funding")
public class ApplicantFundingEntity {

	@Id
	private long id;
	private long amount;
	 @Column(name = "expenses_amount")
	private long expensesAmount;

	 @Column(name = "school_needs_amount")
	private long schoolNeedsAmount;

	 @Column(name = "physical_health_amount")
	private long physicalHealthAmount;

	private String duration;

	private String purpose;
	
	@Column(name = "purpose_description")
	private String purposeDescription;
	 @Column(name = "currency_code")
	private String currencyCode;

	@Column(name = "application_id")
	private long applicationId;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@Column(name = "last_updated_by")
	private String lastUpdatedBy;

	@Column(name = "last_updated_date")
	private LocalDateTime lastUpdatedDate;

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public LocalDateTime getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
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
