package org.brightlife.api.service.repository;

import java.time.LocalDateTime;

import org.brightlife.api.service.entity.StudentIncomeDetailsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentIncomeDetailsRepo extends CrudRepository<StudentIncomeDetailsEntity, Long> {

	@Query(value = "select * from applicant_income where application_id = ?1", nativeQuery = true)
	StudentIncomeDetailsEntity getIncomeDetails(@Param("application_id") long appId);

	@Modifying
	@Query(value = "update applicant_income set annual_income = ?1 , currency = ?2 , earning_person = ?3 , relation_with_student = ?4,"
			+ "last_updated_by = ?5 , last_updated_date = ?6 where id = ?7", nativeQuery = true)
	int updateIncomeDetails(@Param("annual_income") long annualIncome, @Param("currency") String currrency,
			@Param("earning_person") String earningPerson, @Param("relation_with_student") String relationWithStudent,
			@Param("last_updated_by") String lastUpdatedBy, @Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("id") Long id);

}
