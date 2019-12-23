package org.brightlife.api.service.model.dto.request.password;

public class ResendOTPRequest extends PasswordRequest {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ResendOTPRequest(String email) {
		super();
		this.email = email;
	}

}
