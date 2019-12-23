package org.brightlife.api.service.model.dto.request.studentapplication;

public class StudentParentRequest extends StudentApplicationRequest {

	private String livesWithOrIn;
	private String fatherName;
	private String fatherProfession;
	private String motherName;
	private String motherProfession;
	private long annualIncome;
	private int extraAllowance;
	private int familyMembers;
	private String member1Name;
	private int member1Age;
	private String member2Name;
	private int member2Age;
	private String member3Name;
	private int member3Age;
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

	public String getFatherName() {
		return fatherName;
	}

	public String getFatherProfession() {
		return fatherProfession;
	}

	public String getMotherName() {
		return motherName;
	}

	public String getMotherProfession() {
		return motherProfession;
	}

	public long getAnnualIncome() {
		return annualIncome;
	}

	public int getExtraAllowance() {
		return extraAllowance;
	}

	public int getFamilyMembers() {
		return familyMembers;
	}

	public String getMember1Name() {
		return member1Name;
	}

	public int getMember1Age() {
		return member1Age;
	}

	public String getMember2Name() {
		return member2Name;
	}

	public int getMember2Age() {
		return member2Age;
	}

	public String getMember3Name() {
		return member3Name;
	}

	public int getMember3Age() {
		return member3Age;
	}

}
