package org.brightlife.api.service.model.dto.request.auth;

import org.brightlife.api.service.handler.AuthenticationHandler;
import org.brightlife.api.service.handler.RequestHandler;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.service.ServiceHost;

public abstract class AuthenticationRequest extends Request{

	@Override
	public RequestHandler getHandler(ServiceHost host) {
		return new AuthenticationHandler(host);
	}

}
