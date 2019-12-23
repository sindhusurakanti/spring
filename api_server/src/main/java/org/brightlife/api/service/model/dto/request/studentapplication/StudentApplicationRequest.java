package org.brightlife.api.service.model.dto.request.studentapplication;

import org.brightlife.api.service.handler.RequestHandler;
import org.brightlife.api.service.handler.StudentApplicationHandler;
import org.brightlife.api.service.model.dto.request.UserRequest;
import org.brightlife.api.service.service.ServiceHost;

public abstract class StudentApplicationRequest extends UserRequest{

	@Override
	public RequestHandler getHandler(ServiceHost host) {
		return new StudentApplicationHandler(host);
	}

}
