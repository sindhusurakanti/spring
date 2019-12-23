package org.brightlife.api.service.model.dto.request.password;

public class ForgotPasswordRequest extends PasswordRequest {

	private String email;

	public ForgotPasswordRequest(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
}
