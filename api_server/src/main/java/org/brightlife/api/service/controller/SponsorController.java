package org.brightlife.api.service.controller;

import org.brightlife.api.service.handler.RequestProcessor;
import org.brightlife.api.service.model.dto.request.sponsor.SponsorProfileEditRequest;
import org.brightlife.api.service.model.dto.request.sponsor.SponsorProfileRequest;
import org.brightlife.api.service.model.dto.request.sponsor.SponsoredStudentRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("service/api/v1")
public class SponsorController extends BaseController{

	@Autowired
	private RequestProcessor requestProcessor;
	
	@RequestMapping(value = "sponsor/profile", method = RequestMethod.POST)
	public APIResponse profile(@RequestBody SponsorProfileRequest profile, @RequestHeader(value = "Session-Id", required = true) String sessionId) {
		return requestProcessor.handleRequest(sessionId, profile);
	}
	
	@RequestMapping(value = "sponsor/profile/edit", method = RequestMethod.POST)
	public APIResponse editProfile(@RequestHeader(value = "Session-Id", required = true) String sessionId) {
		SponsorProfileEditRequest profile = new SponsorProfileEditRequest();
		return requestProcessor.handleRequest(sessionId, profile);
	}
	
	/*@RequestMapping(value = "sponsor/student", method = RequestMethod.POST)
	public APIResponse sponsorStudent(@RequestBody SponsorStudentRequest req,  @RequestHeader(value = "Session-Id", required = true) String sessionId) {
		return requestProcessor.handleRequest(sessionId, req);
	}*/
	
	@RequestMapping(value = "sponsor/getSponsoredStudents", method = RequestMethod.POST)
	public APIResponse getSponsoredStudents(@RequestHeader(value = "Session-Id", required = true) String sessionId) {
		
		SponsoredStudentRequest req = new SponsoredStudentRequest();
		return requestProcessor.handleRequest(sessionId, req);
	}
}
