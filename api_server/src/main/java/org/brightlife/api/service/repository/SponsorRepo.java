package org.brightlife.api.service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.brightlife.api.service.entity.SponsorEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SponsorRepo extends CrudRepository<SponsorEntity, Long> {

	@Modifying
	@Query(value = "update sponsor set sponsored_student_id = concat(  sponsored_student_id  , ',' , ?1 ) , last_updated_by = ?2, last_updated_date = ?3 where user_id = ?4", nativeQuery = true)
	int updateSponsorWithStudent(@Param("sponsored_student_id") Long studentId, @Param("last_updated_by") String lastUpdatedBy,
			@Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("user_id") long userid);

	@Query(value = "select sponsored_student_id from sponsor where user_id = ?1", nativeQuery = true)
	String getSponsoredStudentIds(@Param("user_id") long userId);

	@Query(value = "SELECT * FROM sponsor WHERE sponsored_student_id like %?1%", nativeQuery = true)
	List<SponsorEntity> findStudent(@Param("sponsored_student_id") String studentProfileId);

	@Query(value = "select * from sponsor where user_id = ?1", nativeQuery = true)
	SponsorEntity findSponsorProfile(@Param("user_id") long userId);

	@Modifying
	@Query(value = "update sponsor set address = ?1 , brightlife_info = ?2 , city = ?3 , country = ?4 , email_address = ?5, firstname = ?6, lastname = ?7,"
			+ " mobile_number = ?8, organisation = ?9, pin_code = ?10, state = ?11, last_updated_by = ?12,last_updated_date =?13, user_id = ?14 where id = ?15", nativeQuery = true)
	int updateSponsorProfile(@Param("address") String address, @Param("brightlife_info") String infoAbtBrightLife, @Param("city") String city,
			@Param("country") String country, @Param("email_address") String email, @Param("firstname") String firstname,
			@Param("lastname") String lastname, @Param("mobile_number") Long mobileNo, @Param("organisation") String organisation,
			@Param("pin_code") Long pincode, @Param("state") String state, @Param("last_updated_by") String lastUpdatedBy,
			@Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("user_id") Long userID, @Param("id") Long id);

}
