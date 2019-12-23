package org.brightlife.api.service.model.dto.request.validation;

import org.brightlife.api.service.handler.RequestHandler;
import org.brightlife.api.service.handler.ValidationHandler;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.service.ServiceHost;

public abstract class ValidationRequest extends Request{
	@Override
	public RequestHandler getHandler(ServiceHost host) {
		return new ValidationHandler(host);
	}

}
