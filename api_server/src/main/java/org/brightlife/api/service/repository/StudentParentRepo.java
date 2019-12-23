package org.brightlife.api.service.repository;

import java.time.LocalDateTime;

import org.brightlife.api.service.entity.StudentParentEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentParentRepo extends CrudRepository<StudentParentEntity, Long> {

	@Query(value = "SELECT * FROM student_parent WHERE application_id = ?1", nativeQuery = true)
	StudentParentEntity findByAppId(@Param("application_id") long appId);

	@Modifying
	@Query(value = "update student_parent set annual_income = ?1, extra_allowance = ?2, family_members = ?3, father_name = ?4,"
			+ "father_profession = ?5, member1_age = ?6, member1_name = ?7, member2_age = ?8, member2_name = ?9, member3_age = ?10,"
			+ "member3_name = ?11, mother_name = ?12, mother_profession = ?13, stays_with = ?14, last_updated_by = ?15, last_updated_date = ?16 where id = ?17", nativeQuery = true)
	int updateParentsDetails(@Param("annual_income") long annualIncome, @Param("extra_allowance") int extraAllowance,
			@Param("family_members") int familyMembers, @Param("father_name") String fatherName, @Param("father_profession") String fatherProfession,
			@Param("member1_age") int member1Age, @Param("member1_name") String member1Name, @Param("member2_age") int member2Age,
			@Param("member2_name") String member2Name, @Param("member3_age") int member3Age, @Param("member3_name") String member3Name,
			@Param("mother_name") String motherName, @Param("mother_profession") String motherProfession, @Param("stays_with") String staysWith,
			@Param("last_updated_by") String lastUpdatedBy, @Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("id") Long id);
}
