package org.brightlife.api.service.model;

import org.brightlife.api.service.model.dto.response.ResponseData;

public class StudentParentDetails extends ResponseData{

	private Long id;
	private Long applicationId;
	private String staysWith;
	private String fatherName;
	private String fatherProfession;
	private String motherName;
	private String motherProfession;
	private Long annualIncome;
	private int extraAllowance;
	private int familyMembers;
	private String member1Name;
	private int member1Age;
	private String member2Name;
	private int member2Age;
	private String member3Name;
	private int member3Age;

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

	public String getStaysWith() {
		return staysWith;
	}

	public void setStaysWith(String staysWith) {
		this.staysWith = staysWith;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherProfession() {
		return fatherProfession;
	}

	public void setFatherProfession(String fatherProfession) {
		this.fatherProfession = fatherProfession;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherProfession() {
		return motherProfession;
	}

	public void setMotherProfession(String motherProfession) {
		this.motherProfession = motherProfession;
	}

	public Long getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(Long annualIncome) {
		this.annualIncome = annualIncome;
	}

	public int getExtraAllowance() {
		return extraAllowance;
	}

	public void setExtraAllowance(int extraAllowance) {
		this.extraAllowance = extraAllowance;
	}

	public int getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(int familyMembers) {
		this.familyMembers = familyMembers;
	}

	public String getMember1Name() {
		return member1Name;
	}

	public void setMember1Name(String member1Name) {
		this.member1Name = member1Name;
	}

	public int getMember1Age() {
		return member1Age;
	}

	public void setMember1Age(int member1Age) {
		this.member1Age = member1Age;
	}

	public String getMember2Name() {
		return member2Name;
	}

	public void setMember2Name(String member2Name) {
		this.member2Name = member2Name;
	}

	public int getMember2Age() {
		return member2Age;
	}

	public void setMember2Age(int member2Age) {
		this.member2Age = member2Age;
	}

	public String getMember3Name() {
		return member3Name;
	}

	public void setMember3Name(String member3Name) {
		this.member3Name = member3Name;
	}

	public int getMember3Age() {
		return member3Age;
	}

	public void setMember3Age(int member3Age) {
		this.member3Age = member3Age;
	}

}
