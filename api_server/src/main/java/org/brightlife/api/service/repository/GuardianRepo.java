package org.brightlife.api.service.repository;

import java.time.LocalDateTime;

import org.brightlife.api.service.entity.GuardianEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GuardianRepo extends CrudRepository<GuardianEntity, Long> {

	@Query(value = "SELECT * FROM guardian WHERE user_id = ?1", nativeQuery = true)
	GuardianEntity findGuardian(@Param("user_id") long userId);

	/*
	 * @Modifying
	 * 
	 * @Query(value =
	 * "update guardian set applied_student_id =  concat(  applied_student_id  , ',' , ?1 ) WHERE id = ?2"
	 * , nativeQuery = true) void
	 * updateAppliedStudentId(@Param("applied_student_id") long studentId,
	 * 
	 * @Param("id") long guardianId);
	 */
	@Query(value = "select applied_student_id from guardian where user_id = ?1", nativeQuery = true)
	String getGuardianAppliedStudentIds(long userId);

	@Modifying
	@Query(value = "update guardian set address = ?1 , brightlife_info = ?2 , city = ?3 , country = ?4 , email_address = ?5, firstname = ?6, lastname = ?7,"
			+ " mobile_number = ?8, organisation = ?9, pin_code = ?10, state = ?11, last_updated_by = ?12,last_updated_date =?13, user_id = ?14 where id = ?15", nativeQuery = true)
	int updateGuardianProfile(@Param("address") String address, @Param("brightlife_info") String infoAbtBrightLife, @Param("city") String city,
			@Param("country") String country, @Param("email_address") String email, @Param("firstname") String firstname,
			@Param("lastname") String lastname, @Param("mobile_number") Long mobileNo, @Param("organisation") String organisation,
			@Param("pin_code") Long pincode, @Param("state") String state, @Param("last_updated_by") String lastUpdatedBy,
			@Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("user_id") Long userID, @Param("id") Long id);

}
