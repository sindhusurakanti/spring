package org.brightlife.api.service.repository;

import java.time.LocalDateTime;

import org.brightlife.api.service.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<UserEntity, Long> {

	@Query(value = "SELECT * FROM user WHERE EMAIL = ?1 ORDER BY created_date DESC LIMIT 1 ", nativeQuery = true)
	UserEntity findByEmail(@Param("EMAIL") String email);

	@Query(value = "SELECT * FROM user WHERE CODE = ?1 ORDER BY code DESC LIMIT 1", nativeQuery = true)
	UserEntity findCode(@Param("CODE") String code);

	@Modifying
	@Query(value = "UPDATE user SET password = ?1 , salt = ?2,last_updated_date = ?3 , is_verified =?4 WHERE EMAIL = ?5", nativeQuery = true)
	int updatePassword(@Param("password") String password, @Param("salt") String salt,
			@Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("is_verified") boolean isVerified,
			@Param("EMAIL") String email);

	@Query(value = "SELECT * FROM user WHERE id = ?1", nativeQuery = true)
	UserEntity getOldPasswordAndSalt(@Param("id") long id);

	@Modifying
	@Query(value = "UPDATE user SET facebook_id = ?1 , last_updated_date = ?2 WHERE email = ?3", nativeQuery = true)
	void updateFacebookId(@Param("facebook_id") String facebookId,
			@Param("last_updated_date") LocalDateTime localDateTime, @Param("email") String email);

	@Query(value = "SELECT * FROM user WHERE facebook_id = ?1", nativeQuery = true)
	UserEntity findFacebookId(@Param("facebook_id") String facebookId);

	@Query(value = "SELECT * FROM user WHERE google_id = ?1", nativeQuery = true)
	UserEntity findGoogleId(@Param("google_id") String googleId);

	@Modifying
	@Query(value = "UPDATE user SET google_id = ?1 , last_updated_date = ?2 WHERE email = ?3", nativeQuery = true)
	void updateGoogleId(@Param("google_id") String facebookId, @Param("last_updated_date") LocalDateTime localDateTime,
			@Param("email") String email);

	@Modifying
	@Query(value = "update user set is_verified = ?1 , last_updated_date = ?2 where EMAIL = ?3 ", nativeQuery = true)
	void updateIsVerified(@Param("is_verified") boolean otpVerified, @Param("last_updated_date")LocalDateTime localDateTime, @Param("EMAIL") String email);
}