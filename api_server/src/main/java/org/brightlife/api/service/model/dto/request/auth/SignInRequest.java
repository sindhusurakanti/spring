package org.brightlife.api.service.model.dto.request.auth;

public class SignInRequest extends AuthenticationRequest {

	private String email;
	private String password;

	public SignInRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
