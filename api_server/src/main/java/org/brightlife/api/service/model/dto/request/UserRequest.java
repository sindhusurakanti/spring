package org.brightlife.api.service.model.dto.request;

import org.brightlife.api.service.model.UserSession;

public abstract class UserRequest extends Request {
	private UserSession session;
	
	public void setSession(UserSession session) {
		this.session = session;
	}

	public UserSession getSession() {
		return session;
	}

}
