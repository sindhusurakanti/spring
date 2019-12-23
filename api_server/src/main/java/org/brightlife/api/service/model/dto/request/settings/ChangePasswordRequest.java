package org.brightlife.api.service.model.dto.request.settings;

public class ChangePasswordRequest extends SettingsRequest {

	private String password;
	private String newPassword;

	public ChangePasswordRequest(String password, String newPassword) {
		super();
		this.password = password;
		this.newPassword = newPassword;
	}

	public String getPassword() {
		return password;
	}

	public String getNewPassword() {
		return newPassword;
	}

}
