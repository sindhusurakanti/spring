package org.brightlife.api.service.model.dto.response;

import java.util.Date;

public class StudentFilteredResponse extends ResponseData {

	private long appId;
	private long studentId;
	private long userId;
	private String profilePicUrl;
	private String fullName;
	private Date dateOfBirth;
	private String schoolName;
	private String city;
	private String livesWithOrIn;
	private long amount;
	private String country;
	private Long income;
	private String hobbies;
	private String aspirations;
	private String orphanageName;
	private Long orphanageContact;
	private String orphanageAddress;
	private Long orphanagePinCode;
	private String orphanMother;
	private String orphanFather;
	private Long daysWaiting;

	public String getOrphanageName() {
		return orphanageName;
	}

	public void setOrphanageName(String orphanageName) {
		this.orphanageName = orphanageName;
	}

	public Long getOrphanageContact() {
		return orphanageContact;
	}

	public void setOrphanageContact(Long orphanageContact) {
		this.orphanageContact = orphanageContact;
	}

	public String getOrphanageAddress() {
		return orphanageAddress;
	}

	public void setOrphanageAddress(String orphanageAddress) {
		this.orphanageAddress = orphanageAddress;
	}

	public Long getOrphanagePinCode() {
		return orphanagePinCode;
	}

	public void setOrphanagePinCode(Long orphanagePinCode) {
		this.orphanagePinCode = orphanagePinCode;
	}

	public String getOrphanMother() {
		return orphanMother;
	}

	public void setOrphanMother(String orphanMother) {
		this.orphanMother = orphanMother;
	}

	public String getOrphanFather() {
		return orphanFather;
	}

	public void setOrphanFather(String orphanFather) {
		this.orphanFather = orphanFather;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getAspirations() {
		return aspirations;
	}

	public void setAspirations(String aspirations) {
		this.aspirations = aspirations;
	}

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLivesWithOrIn() {
		return livesWithOrIn;
	}

	public void setLivesWithOrIn(String livesWithOrIn) {
		this.livesWithOrIn = livesWithOrIn;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getIncome() {
		return income;
	}

	public void setIncome(Long income) {
		this.income = income;
	}

	public Long getDaysWaiting() {
		return daysWaiting;
	}

	public void setDaysWaiting(Long daysWaiting) {
		this.daysWaiting = daysWaiting;
	}

}
