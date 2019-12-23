package org.brightlife.api.service.model.dto.request.studentapplication;

public class StudentBankDetailsRequest extends StudentApplicationRequest {

	private Long accountNo;
	private String ifscCode;
	private String relationWithApplicant;
	private String branch;
	private String holderName;
	private String bankCode;
	private String branchAddress;
	private String state;
	private String country;
	private Long pincode;
	private String indicator;
	private Long id;

	public Long getId() {
		return id;
	}

	public Long getAccountNo() {
		return accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public String getRelationWithApplicant() {
		return relationWithApplicant;
	}

	public String getBranch() {
		return branch;
	}

	public String getHolderName() {
		return holderName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public Long getPincode() {
		return pincode;
	}

	public String getIndicator() {
		return indicator;
	}

}