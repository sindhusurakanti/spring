package org.brightlife.api.service.model.dto.request.studentapplication;

public class StudentIncomeDetailsRequest extends StudentApplicationRequest {

	private String earningPerson;
	private String relationWithStudent;
	private long annualIncome;
	private String currency;
	private String indicator;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEarningPerson() {
		return earningPerson;
	}

	public String getRelationWithStudent() {
		return relationWithStudent;
	}

	public long getAnnualIncome() {
		return annualIncome;
	}

	public String getCurrency() {
		return currency;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

}
