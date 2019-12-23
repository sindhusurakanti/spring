package org.brightlife.api.service.model;

import org.brightlife.api.service.model.dto.response.ResponseData;

public class StudentIncomeDetails extends ResponseData {

	private String earningPerson;
	private String relationWithStudent;
	private Long annualIncome;
	private String currency;
	private Long id;

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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
