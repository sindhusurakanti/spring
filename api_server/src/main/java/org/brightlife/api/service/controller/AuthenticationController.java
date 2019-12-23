package org.brightlife.api.service.controller;

import org.brightlife.api.service.handler.RequestProcessor;
import org.brightlife.api.service.model.dto.request.auth.SignInRequest;
import org.brightlife.api.service.model.dto.request.auth.SignUpRequest;
import org.brightlife.api.service.model.dto.request.auth.SocialSignInRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/service/api/v1")
public class AuthenticationController extends BaseController {

	@Autowired
	private RequestProcessor requestProcessor;

	@PostMapping(value = "/signup/email")
	public APIResponse signUp(@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password) {
		System.out.println(name + " " + email + " " + password);
		SignUpRequest req = new SignUpRequest(name, email, password);
		return requestProcessor.handleRequest(req);

	}

	@RequestMapping(value = "/login/email", method = RequestMethod.POST)
	public APIResponse login(@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "password", required = true) String password) {

		SignInRequest req = new SignInRequest(email, password);
		return requestProcessor.handleRequest(req);
	}

	@PostMapping("/login/social")
	public APIResponse socialAccount(@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "socialToken", required = true) String token,
			@RequestParam(value = "identifier", required = true) String identifier) {
		SocialSignInRequest req = new SocialSignInRequest(token, email, identifier);

		return requestProcessor.handleRequest(req);
	}

}
