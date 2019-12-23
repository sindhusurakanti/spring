package org.brightlife.api.service.controller;

import org.brightlife.api.service.handler.RequestProcessor;
import org.brightlife.api.service.model.dto.request.settings.AssignRoleRequest;
import org.brightlife.api.service.model.dto.request.settings.ChangePasswordRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/service/api/v1")
public class SettingsController extends BaseController {

	@Autowired
	private RequestProcessor requestProcessor;

	@PostMapping("password/change")
	public APIResponse changePasswordValidation(@RequestHeader(value = "Session-Id", required = true) String sessionId,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "newPassword", required = true) String newPassword) {

		ChangePasswordRequest req = new ChangePasswordRequest(password, newPassword);
		return requestProcessor.handleRequest(sessionId, req);

	}

	@PostMapping("assign/role")
	public APIResponse assignRole(@RequestParam(name = "role", required = true) String role,
			@RequestHeader(value = "Session-Id", required = true) String sessionId) {
		AssignRoleRequest req = new AssignRoleRequest(role);
		return requestProcessor.handleRequest(sessionId, req);
	}

}