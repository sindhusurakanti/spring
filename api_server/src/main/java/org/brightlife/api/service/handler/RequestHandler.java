package org.brightlife.api.service.handler;

import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.model.dto.response.APIResponse;
import org.brightlife.api.service.model.dto.response.ValidationErrorData;
import org.brightlife.api.service.service.ServiceHost;

public abstract class RequestHandler {
	
	ServiceHost host;
	
	public RequestHandler(ServiceHost host) {
		this.host = host;
	}
	
	public abstract APIResponse process(Request req);
	
	public abstract ValidationErrorData validate(Request req);
	
	
}
