package org.brightlife.api.service.model.dto.request.validation;

public class VerifyEmailWithOTPRequest extends ValidationRequest {

	private String email;
	private String otp;
	public VerifyEmailWithOTPRequest(String email, String otp) {
		super();
		this.email = email;
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public String getOtp() {
		return otp;
	}

}
