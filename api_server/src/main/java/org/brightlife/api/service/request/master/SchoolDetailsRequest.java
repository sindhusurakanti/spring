package org.brightlife.api.service.request.master;

import org.brightlife.api.service.handler.MasterDataHandler;
import org.brightlife.api.service.handler.RequestHandler;
import org.brightlife.api.service.model.dto.request.Request;
import org.brightlife.api.service.service.ServiceHost;

public class SchoolDetailsRequest extends Request {


	public SchoolDetailsRequest(String name) {
		super();
		this.name = name;
	}

	private String name;

	@Override
	public RequestHandler getHandler(ServiceHost host) {
		return new MasterDataHandler(host);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
