package org.brightlife.api.service.model.dto.request.studentapplication;

public class FilterRequest extends StudentApplicationRequest {

	private String name;
	private String city;
	private String livesWithOrIn;
	private String country;
	private String birthMonth;
	private String gender;
	private String ageRange;
	private String state;
	private String income;
	private String orphan;

	public FilterRequest(String name, String city, String livesWithOrIn, String country, String birthMonth, String gender, String ageRange,
			String state, String income, String orphan) {
		super();
		this.name = name;
		this.city = city;
		this.livesWithOrIn = livesWithOrIn;
		this.country = country;
		this.birthMonth = birthMonth;
		this.gender = gender;
		this.ageRange = ageRange;
		this.state = state;
		this.income = income;
		this.orphan = orphan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getOrphan() {
		return orphan;
	}

	public void setOrphan(String orphan) {
		this.orphan = orphan;
	}

}
