package org.brightlife.api.service.controller;

import java.security.NoSuchAlgorithmException;

import org.brightlife.api.service.handler.RequestProcessor;
import org.brightlife.api.service.model.dto.request.password.ChangePasswordWithOTPRequest;
import org.brightlife.api.service.model.dto.request.password.ForgotPasswordRequest;
import org.brightlife.api.service.model.dto.request.password.ResendOTPRequest;
import org.brightlife.api.service.model.dto.request.validation.VerifyEmailWithOTPRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/service/api/v1")
public class VisitorController {

	@Autowired
	private RequestProcessor requestProcessor;

	@PostMapping("password/forgot")
	public APIResponse forgotPassword(@RequestParam(value = "email", required = true) String email)
			throws NoSuchAlgorithmException {

		ForgotPasswordRequest req = new ForgotPasswordRequest(email);
		return requestProcessor.handleRequest(req);

	}
	@PostMapping("password/change/with/otp")
	public APIResponse changePasswordWithOTP(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "otp", required = true) String otp) {

		ChangePasswordWithOTPRequest req = new ChangePasswordWithOTPRequest(email, password, otp);
		return requestProcessor.handleRequest(req);

	}

	@PostMapping("verify/email/with/otp")
	public APIResponse validateEmailWithOTP(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "otp", required = true) String otp) {

		VerifyEmailWithOTPRequest req = new VerifyEmailWithOTPRequest(email, otp);
		return requestProcessor.handleRequest(req);
	}

	@PostMapping("resend/otp")
	public APIResponse resendOTP(@RequestParam(value = "email", required = true) String email) {
		ResendOTPRequest req = new ResendOTPRequest(email);
		return requestProcessor.handleRequest(req);
	}
}
