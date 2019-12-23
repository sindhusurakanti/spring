package org.brightlife.api.service.model.dto.request.payments;

import org.brightlife.api.service.handler.PaymentsHandler;
import org.brightlife.api.service.handler.RequestHandler;
import org.brightlife.api.service.model.dto.request.UserRequest;
import org.brightlife.api.service.service.ServiceHost;

public abstract class PaymentsRequest extends UserRequest {

	@Override
	public RequestHandler getHandler(ServiceHost host) {

		return new PaymentsHandler(host);
	}
}
