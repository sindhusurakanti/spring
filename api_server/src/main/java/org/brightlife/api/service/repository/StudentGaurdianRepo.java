package org.brightlife.api.service.repository;

import java.time.LocalDateTime;

import org.brightlife.api.service.entity.StudentGuardianEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentGaurdianRepo extends CrudRepository<StudentGuardianEntity, Long> {

	@Query(value = "SELECT * FROM student_guardian WHERE application_id = ?1", nativeQuery = true)
	StudentGuardianEntity findByAppId(@Param("application_id") long appId);

	@Modifying
	@Query(value = "update student_guardian set annual_income = ?1, fathers_name = ?2, mothers_name = ?3, name = ?4,"
			+ " profession = ?5, relation_with_student = ?6, last_updated_by = ?7, last_updated_date = ?8 where id = ?9", nativeQuery = true)
	int updateGaurdianDetails(@Param("annual_income") long annualIncome, @Param("fathers_name") String fathersName,
			@Param("mothers_name") String mothersName, @Param("name") String name, @Param("profession") String profession,
			@Param("relation_with_student") String relationWithStudent, @Param("last_updated_by") String lastUpdatedBy,
			@Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("id") Long id);
}
