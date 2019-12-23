package org.brightlife.api.service.model.dto.request.auth;

public class SocialSignInRequest extends AuthenticationRequest {
	private String socialToken;
	private String email;
	private String provider;
	
	
	public SocialSignInRequest(String socialToken, String email, String provider) {
		super();
		this.socialToken = socialToken;
		this.email = email;
		this.provider = provider;
	}


	public SocialSignInRequest(String tokenValue, String email) {
		super();
		this.socialToken = tokenValue;
		this.email = email;
	}
	
	
	public String getSocialToken() {
		return socialToken;
	}

	public String getEmail() {
		return email;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	
}
