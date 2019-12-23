package org.brightlife.api.service.model.dto.request.studentapplication;

public class FundingDetailsRequest extends StudentApplicationRequest {

	private long totalFunding;
	private long physicalHealth;
	private long schoolNeeds;
	private long livingExpenses;
	private long duration;
	private Long id;
	private String indicator;
	
	public Long getId() {
		return id;
	}

	public long getTotalFunding() {
		return totalFunding;
	}

	public void setTotalFunding(long totalFunding) {
		this.totalFunding = totalFunding;
	}

	public long getPhysicalHealth() {
		return physicalHealth;
	}

	public void setPhysicalHealth(long physicalHealth) {
		this.physicalHealth = physicalHealth;
	}

	public long getSchoolNeeds() {
		return schoolNeeds;
	}

	public void setSchoolNeeds(long schoolNeeds) {
		this.schoolNeeds = schoolNeeds;
	}

	public long getLivingExpenses() {
		return livingExpenses;
	}

	public void setLivingExpenses(long livingExpenses) {
		this.livingExpenses = livingExpenses;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

}
