package org.brightlife.api.service.model.dto.request.settings;

public class AssignRoleRequest extends SettingsRequest {

	private String role;
	public AssignRoleRequest(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
}
