package org.brightlife.api.service.repository;

import java.time.LocalDateTime;

import org.brightlife.api.service.entity.StudentBankDetailsEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentBankDetailsRepo extends CrudRepository<StudentBankDetailsEntity, Long> {

	@Query(value = " select * from applicant_bank_details where application_id = ?1", nativeQuery = true)
	StudentBankDetailsEntity getBankDetails(@Param("application_id") Long applicationId);

	@Modifying
	@Query(value = "update applicant_bank_details set account_no = ?1, bank_code = ?2, branch = ?3, branch_address = ?4,"
			+ "country = ?5, holder_name = ?6, ifsc_code = ?7, pin_code = ?8, relation_with_applicant = ?9,"
			+ "state = ?10, last_updated_by = ?11, last_updated_date = ?12 where id = ?13", nativeQuery = true)
	int updateBankDetails(@Param("account_no") long accountNo, @Param("bank_code") String bankCode, @Param("branch") String branch,
			@Param("branch_address") String branchAddress, @Param("country") String country, @Param("holder_name") String holderName,
			@Param("ifsc_code") String ifscCode, @Param("pin_code") String pinCode, @Param("relation_with_applicant") String relationWithApplicant,
			@Param("state") String state, @Param("last_updated_by") String lastUpdatedBy, @Param("last_updated_date") LocalDateTime lastUpdatedDate,
			@Param("id") long id);

}
