package org.brightlife.api.service.repository;

import java.util.List;

import org.brightlife.api.service.entity.ApplicantDocumentsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentDocumentRepo extends CrudRepository<ApplicantDocumentsEntity, Long>{

	@Query(value = "SELECT * FROM applicant_document WHERE id = ?1", nativeQuery = true)
	List<ApplicantDocumentsEntity> findByAppId(@Param("id") long applicationID);

}
