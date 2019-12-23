package org.brightlife.api.service.repository;

import java.time.LocalDateTime;

import org.brightlife.api.service.entity.OtpEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OtpRepo extends CrudRepository<OtpEntity, Long> {

	@Query(value = "SELECT * FROM otp WHERE EMAIL = ?1 ORDER BY issued_date DESC LIMIT 1 ", nativeQuery = true)
	OtpEntity findOtpDetails(@Param("EMAIL") String email);

	@Modifying
	@Query(value = "update otp set is_otp_verified = ?1, last_updated_date = ?2 where EMAIL = ?3 ", nativeQuery = true)
	void updateOTPVerifiedStatus(@Param("is_otp_verified") boolean otpVerified,
			@Param("last_updated_date") LocalDateTime localDateTime, @Param("EMAIL") String email);
}
