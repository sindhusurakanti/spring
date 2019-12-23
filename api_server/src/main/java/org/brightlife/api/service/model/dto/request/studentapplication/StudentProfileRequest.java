package org.brightlife.api.service.model.dto.request.studentapplication;

import java.util.Date;

public class StudentProfileRequest extends StudentApplicationRequest {

	private String fileName;
	private String fullName;
	private Date dateOfBirth;
	private String languageSpoken;
	private String gender;
	private String doorNo;
	private String street;
	private String city;
	private String district;
	private String state;
	private String country;
	private String postalCode;
	private long income;
	private Long id;
	private String indicator;

	public StudentProfileRequest(String fileName, String fullName, Date dateOfBirth, String languageSpoken, String gender, String doorNo,
			String street, String city, String district, String state, String country, String postalCode, long income) {
		super();
		this.fileName = fileName;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.languageSpoken = languageSpoken;
		this.gender = gender;
		this.doorNo = doorNo;
		this.street = street;
		this.city = city;
		this.district = district;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.income = income;
	}

	

	public Long getId() {
		return id;
	}

	public String getIndicator() {
		return indicator;
	}

	public long getIncome() {
		return income;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFullName() {
		return fullName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getLanguageSpoken() {
		return languageSpoken;
	}

	public String getGender() {
		return gender;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getDistrict() {
		return district;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getPostalCode() {
		return postalCode;
	}

}
