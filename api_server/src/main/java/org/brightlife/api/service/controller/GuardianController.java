package org.brightlife.api.service.controller;

import org.brightlife.api.service.handler.RequestProcessor;
import org.brightlife.api.service.model.dto.request.guardian.GuardianAppliedStudentsRequest;
import org.brightlife.api.service.model.dto.request.guardian.GuardianProfileEditRequest;
import org.brightlife.api.service.model.dto.request.guardian.GuardianProfileRequest;
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
public class GuardianController extends BaseController{

	@Autowired
	private RequestProcessor requestProcessor;
	
	@RequestMapping(value = "guardian/profile", method = RequestMethod.POST)
	public APIResponse profile(@RequestBody GuardianProfileRequest profile, @RequestHeader(value = "Session-Id", required = true) String sessionId) {
		return requestProcessor.handleRequest(sessionId, profile);
	}
	
	@RequestMapping(value = "guardian/appliedStudents", method = RequestMethod.POST)
	public APIResponse appliedStudents( @RequestHeader(value = "Session-Id", required = true) String sessionId) {
		
		GuardianAppliedStudentsRequest req = new GuardianAppliedStudentsRequest();
		return requestProcessor.handleRequest(sessionId, req);
	}
	@RequestMapping(value = "guardian/profile/edit", method = RequestMethod.POST)
	public APIResponse editProfile(@RequestHeader(value = "Session-Id", required = true) String sessionId) {
		 GuardianProfileEditRequest profile = new GuardianProfileEditRequest();
		return requestProcessor.handleRequest(sessionId, profile);
	}
}
