package org.brightlife.api.service.model.dto.request.studentapplication;

public class StudentGuardianRequest extends StudentApplicationRequest {

	private String livesWithOrIn;
	private String name;
	private String relationWithStudent;
	private String profession;
	private long annualIncome;
	private boolean parentDetailsAvailable;
	private String fathersName;
	private String mothersName;
	private Long id;
	private String indicator;

	public Long getId() {
		return id;
	}

	public String getIndicator() {
		return indicator;
	}

	public String getLivesWithOrIn() {
		return livesWithOrIn;
	}

	public String getName() {
		return name;
	}

	public String getRelationWithStudent() {
		return relationWithStudent;
	}

	public String getProfession() {
		return profession;
	}

	public long getAnnualIncome() {
		return annualIncome;
	}

	public boolean isParentDetailsAvailable() {
		return parentDetailsAvailable;
	}

	public String getFathersName() {
		return fathersName;
	}

	public String getMothersName() {
		return mothersName;
	}

}
