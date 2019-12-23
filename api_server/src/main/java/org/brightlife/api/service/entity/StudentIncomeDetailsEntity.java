package org.brightlife.api.service.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applicant_income")
public class StudentIncomeDetailsEntity {

	@Id
	private long id;

	@Column(name = "application_id")
	private Long applicationId;

	@Column(name = "earning_person")
	private String earningPerson;

	@Column(name = "relation_with_student")
	private String relationWithStudent;

	@Column(name = "annual_income")
	private Long annualIncome;

	@Column(name = "currency")
	private String currency;

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


	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getEarningPerson() {
		return earningPerson;
	}

	public void setEarningPerson(String earningPerson) {
		this.earningPerson = earningPerson;
	}

	public String getRelationWithStudent() {
		return relationWithStudent;
	}

	public void setRelationWithStudent(String relationWithStudent) {
		this.relationWithStudent = relationWithStudent;
	}

	public Long getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(Long annualIncome) {
		this.annualIncome = annualIncome;
	}

	public String getCurrrency() {
		return currency;
	}

	public void setCurrrency(String currrency) {
		this.currency = currrency;
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
}
