package org.brightlife.api.service.handler;

import org.brightlife.api.service.consts.ErrorConstants;
import org.brightlife.api.service.model.UserSession;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.request.UserRequest;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.service.ServiceHost;
import org.brightlife.api.service.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestProcessor {
	
	@Autowired
	ServiceHost host;
	
	public APIResponse handleRequest(Request req) {
		try {
			RequestHandler handler = req.getHandler(host);
			ValidationErrorData errData = handler.validate(req); 
			
			if(errData.hasErrors()) {
				return ResponseUtils.errorResponse(errData);
			}
			System.out.println("Processor");
			return handler.process(req);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			return ResponseUtils.errorResponse(ErrorConstants.UNKNOWN_ERROR);
		}
	}
	
	private UserSession getUserSession(String sessionId) {
		return host.getSessionService().getSession(sessionId);
	}

	private APIResponse invalidSessionResponse() {
		return ResponseUtils.errorResponse(ErrorConstants.INVALID_SESSION);
	}
	
	public APIResponse handleRequest(String sessionId, UserRequest req) {
		UserSession session = getUserSession(sessionId);
		if (session == null)
			return invalidSessionResponse();
		req.setSession(session);
		return handleRequest(req);
	}

}