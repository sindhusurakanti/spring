package org.brightlife.api.service.model.dto.request.guardian;

import org.brightlife.api.service.handler.GuardianHandler;
import org.brightlife.api.service.handler.RequestHandler;
import org.brightlife.api.service.model.dto.request.UserRequest;
import org.brightlife.api.service.service.ServiceHost;

public abstract class GuardianProfile extends UserRequest {

	@Override
	public RequestHandler getHandler(ServiceHost host) {
		
		return new GuardianHandler(host);
	}

}
