package org.brightlife.api.service.service;

import java.time.LocalDateTime;

import org.brightlife.api.service.entity.UserSessionEntity;
import org.brightlife.api.service.model.UserSession;
import org.brightlife.api.service.repository.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

	@Autowired
	private SessionRepo sessionRepo;

	public boolean isValidSession(String sessionId) {
		UserSessionEntity session = sessionRepo.findSessionById(sessionId);
		return session == null ? false : true;
	}

	public UserSession getSession(String sessionId) {
		UserSessionEntity sessionEntity = sessionRepo.findSessionById(sessionId);
		if (sessionEntity != null) {
			UserSession session = new UserSession();
			session.setSessionId(sessionId);
			session.setUserId(sessionEntity.getUserId());
			session.setLoginTime(sessionEntity.getLoginTime());
			return session;
		} else {
			return null;
		}
	}

	public UserSession createSession(long id) {

		UserSessionEntity sessionEntity = new UserSessionEntity();
		UserSession userSession = new UserSession();
		sessionEntity.setUserId(id);
		sessionEntity.setLoginTime(LocalDateTime.now());
		sessionEntity.setCreatedDate(LocalDateTime.now());
		sessionEntity.setLastUpdatedDate(LocalDateTime.now());
		sessionRepo.save(sessionEntity);

		sessionEntity = sessionRepo.findSessionByUserId(id);

		userSession.setId(sessionEntity.getId());
		userSession.setSessionId(sessionEntity.getSessionId());
		userSession.setLoginTime(sessionEntity.getLoginTime());
		userSession.setUserId(sessionEntity.getUserId());

		return userSession;

	}
}
