package org.brightlife.api.service.model;

import org.brightlife.api.service.model.dto.response.ResponseData;

public class StudentGuardianDetails extends ResponseData {

	private Long id;
	private Long applicationId;
	private String name;
	private String relationWithStudent;
	private String profession;
	private Long annualIncome;
	private boolean parentDetailsAvailable;
	private String fathersName;
	private String mothersName;

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

}
