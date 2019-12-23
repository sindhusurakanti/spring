package org.brightlife.api.service.model;

import org.brightlife.api.service.model.dto.response.ResponseData;

public class ApplicantDetails extends ResponseData{

	private Long id;
	private Long applicationId;
	private String email;
	private int mobileNumber;
	private boolean isHandicapped;
	private int standard;
	private String school;
	private String hobbies;
	private String aspirations;
	private String achievements;
	private String achievementsURL;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isHandicapped() {
		return isHandicapped;
	}

	public void setHandicapped(boolean isHandicapped) {
		this.isHandicapped = isHandicapped;
	}

	public int getStandard() {
		return standard;
	}

	public void setStandard(int standard) {
		this.standard = standard;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
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

	public String getAchievements() {
		return achievements;
	}

	public void setAchievements(String achievements) {
		this.achievements = achievements;
	}

	public String getAchievementsURL() {
		return achievementsURL;
	}

	public void setAchievementsURL(String achievementsURL) {
		this.achievementsURL = achievementsURL;
	}

}
