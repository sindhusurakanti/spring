package org.brightlife.api.service.model.dto.request.sponsor;

import org.brightlife.api.service.handler.RequestHandler;
import org.brightlife.api.service.handler.SponsorHandler;
import org.brightlife.api.service.model.dto.request.UserRequest;
import org.brightlife.api.service.service.ServiceHost;

public abstract class SponsorProfile extends UserRequest {

	@Override
	public RequestHandler getHandler(ServiceHost host) {

		return new SponsorHandler(host);
	}

}
