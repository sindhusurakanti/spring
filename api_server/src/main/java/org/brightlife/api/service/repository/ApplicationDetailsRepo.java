package org.brightlife.api.service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.brightlife.api.service.entity.ApplicantDetailsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ApplicationDetailsRepo extends CrudRepository<ApplicantDetailsEntity, Long> {

	@Query(value = "SELECT * FROM applicant_details WHERE application_id = ?1  ORDER BY created_date DESC LIMIT 1", nativeQuery = true)
	ApplicantDetailsEntity findByAppId(@Param("application_id") long appId);

	@Query(value = "SELECT * FROM applicant_details ", nativeQuery = true)
	List<ApplicantDetailsEntity> findStudents();

	@Modifying
	@Query(value = "update applicant_details set achievements =?1, aspirations = ?4, email = ?5,"
			+ "hobbies = ?6, school = ?7, mobile_number = ?8, class = ?9, performance = ?10, last_updated_by = ?11, last_updated_date = ?12 where id = ?13", nativeQuery = true)
	int updateEducationDetails(@Param("achievements") String achievements, @Param("aspirations") String aspirations, @Param("email") String email,
			@Param("hobbies") String hobbies, @Param("school") String school, @Param("mobile_number") int mobileNumber, @Param("class") int standard,
			@Param("performance") String performance, @Param("last_updated_by") String lastUpdatedBy,
			@Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("id") long id);

}
