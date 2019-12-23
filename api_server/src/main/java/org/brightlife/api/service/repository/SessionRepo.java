package org.brightlife.api.service.repository;

import org.brightlife.api.service.entity.UserSessionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SessionRepo extends CrudRepository<UserSessionEntity, Long> {

	@Query(value = "SELECT * FROM user_session WHERE session_id = ?1", nativeQuery = true)
	UserSessionEntity findSessionById(@Param("session_id") String id);

	@Query(value = "SELECT * FROM user_session WHERE user_id = ?1 order by login_time desc limit 1", nativeQuery = true)
	UserSessionEntity findSessionByUserId(@Param("user_id") long user_id);

}
