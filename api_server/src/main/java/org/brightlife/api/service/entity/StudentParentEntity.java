package org.brightlife.api.service.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_parent")
public class StudentParentEntity {

	@Id
	private long id;

	@Column(name = "application_id")
	private Long applicationId;
	
	@Column(name = "stays_with")
	private String staysWith;
	
	@Column(name = "father_name")
	private String fatherName;
	
	@Column(name = "father_profession")
	private String fatherProfession;
	
	@Column(name = "mother_name")
	private String motherName;
	
	@Column(name = "mother_profession")
	private String motherProfession;
	
	@Column(name = "annual_income")
	private Long annualIncome;
	
	@Column(name = "extra_allowance")
	private int extraAllowance;
	
	@Column(name = "family_members")
	private int familyMembers;
	
	@Column(name = "member1_name")
	private String member1Name;
	
	@Column(name = "member1_age")
	private int member1Age;
	
	@Column(name = "member2_name")
	private String member2Name;
	
	@Column(name = "member2_age")
	private int member2Age;
	
	@Column(name = "member3_name")
	private String member3Name;
	
	@Column(name = "member3_age")
	private int member3Age;
	
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
