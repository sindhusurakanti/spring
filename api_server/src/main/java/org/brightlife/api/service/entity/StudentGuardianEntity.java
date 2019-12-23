package org.brightlife.api.service.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_guardian")
public class StudentGuardianEntity {

	@Id
	private long id;

	@Column(name = "application_id")
	private Long applicationId;
	private String name;

	@Column(name = "relation_with_student")
	private String relationWithStudent;
	private String profession;

	@Column(name = "annual_income")
	private Long annualIncome;

	@Column(name = "parent_details_available")
	private boolean parentDetailsAvailable;

	@Column(name = "fathers_name")
	private String fathersName;

	@Column(name = "mothers_name")
	private String mothersName;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationWithStudent() {
		return relationWithStudent;
	}

	public void setRelationWithStudent(String relationWithStudent) {
		this.relationWithStudent = relationWithStudent;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Long getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(Long annualIncome) {
		this.annualIncome = annualIncome;
	}

	public boolean isParentDetailsAvailable() {
		return parentDetailsAvailable;
	}

	public void setParentDetailsAvailable(boolean parentDetailsAvailable) {
		this.parentDetailsAvailable = parentDetailsAvailable;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
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
