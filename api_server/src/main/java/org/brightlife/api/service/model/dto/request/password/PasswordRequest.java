package org.brightlife.api.service.model.dto.request.password;

import org.brightlife.api.service.handler.PasswordHandler;
import org.brightlife.api.service.handler.RequestHandler;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.service.ServiceHost;

public abstract class PasswordRequest extends Request {

	@Override
	public RequestHandler getHandler(ServiceHost host) {
		return new PasswordHandler(host);
	}

}
