package org.brightlife.api.service.model;

import java.util.Date;

import org.brightlife.api.service.model.dto.response.ResponseData;

public class StudentProfile extends ResponseData{

	private Long id;
	private Long userId;
	private String profilePicUrl;
	private String fullName;
	private Date dateOfBirth;
	private Integer age;
	private String languageSpoken;
	private String gender;
	private String email;
	private String contact;
	private String livesWithOrIn;
	private String doorNo;
	private String street;
	private String city;
	private String district;
	private String state;
	private String country;
	private Integer postalCode;
	private Boolean isOrphan;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLanguageSpoken() {
		return languageSpoken;
	}

	public void setLanguageSpoken(String languageSpoken) {
		this.languageSpoken = languageSpoken;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getLivesWithOrIn() {
		return livesWithOrIn;
	}

	public void setLivesWithOrIn(String livesWithOrIn) {
		this.livesWithOrIn = livesWithOrIn;
	}

	public Boolean getIsOrphan() {
		return isOrphan;
	}

	public void setIsOrphan(Boolean isOrphan) {
		this.isOrphan = isOrphan;
	}

}
