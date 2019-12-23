package org.brightlife.api.service.repository;

import java.time.LocalDateTime;

import org.brightlife.api.service.entity.ApplicantFundingEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ApplicantFundingRepo extends CrudRepository<ApplicantFundingEntity, Long> {

	@Query(value = "SELECT * FROM applicant_funding WHERE application_id = ?1", nativeQuery = true)
	ApplicantFundingEntity findByAppId(@Param("application_id") long appId);

	@Modifying
	@Query(value = "update applicant_funding set amount = ?1, currency_code = ?2, duration = ?3, expenses_amount = ?4,"
			+ "physical_health_amount = ?5, purpose = ?6, purpose_description = ?7, school_needs_amount = ?8, last_updated_by = ?9, last_updated_date = ?10 where id = ?11", nativeQuery = true)
	int updateFundingDetails(@Param("amount") long amount, @Param("currency_code") String currencyCode, @Param("duration") String duration,
			@Param("expenses_amount") long expensesAmount, @Param("physical_health_amount") long physicalHealthAmount,
			@Param("purpose") String purpose, @Param("purpose_description") String purposeDescription,
			@Param("school_needs_amount") long schoolNeedsAmount, @Param("last_updated_by") String lastUpdatedBy,
			@Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("id") Long id);
}
