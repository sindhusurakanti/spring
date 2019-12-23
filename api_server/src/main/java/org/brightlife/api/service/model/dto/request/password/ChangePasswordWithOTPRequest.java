package org.brightlife.api.service.model.dto.request.password;

public class ChangePasswordWithOTPRequest extends PasswordRequest {

	private String email;
	private String password;
	private String otp;

	public ChangePasswordWithOTPRequest(String email, String password, String otp) {
		super();
		this.email = email;
		this.password = password;
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getOtp() {
		return otp;
	}

}
