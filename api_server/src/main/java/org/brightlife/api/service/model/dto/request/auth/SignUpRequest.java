package org.brightlife.api.service.model.dto.request.auth;

public class SignUpRequest extends AuthenticationRequest {
	
	private String name;
	private String email;
	private String password;

	public SignUpRequest(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
