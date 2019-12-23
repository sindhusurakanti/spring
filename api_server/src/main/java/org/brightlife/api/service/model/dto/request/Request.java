package org.brightlife.api.service.model.dto.request;

import org.brightlife.api.service.handler.RequestHandler;
import org.brightlife.api.service.service.ServiceHost;

public abstract class Request {

	public abstract RequestHandler getHandler(ServiceHost host);
}
