package org.brightlife.api.service.model.dto.request.settings;

import org.brightlife.api.service.handler.RequestHandler;
import org.brightlife.api.service.handler.SettingsHandler;
import org.brightlife.api.service.model.dto.request.UserRequest;
import org.brightlife.api.service.service.ServiceHost;

public abstract class SettingsRequest extends UserRequest {

	@Override
	public RequestHandler getHandler(ServiceHost host) {
		return new SettingsHandler(host);
	}

}
