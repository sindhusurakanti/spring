package org.brightlife.api.service.repository;

import java.time.LocalDateTime;

import org.brightlife.api.service.entity.StudentOrphanageEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentOrphanageRepo extends CrudRepository<StudentOrphanageEntity, Long> {

	@Query(value = "SELECT * FROM student_orphanage WHERE application_id = ?1", nativeQuery = true)
	StudentOrphanageEntity findByAppId(@Param("application_id") long appId);

	@Modifying
	@Query(value = "update student_orphanage set address = ?1, contact = ?2, fathers_name = ?3,"
			+ "mothers_name = ?4 , name = ?5, postal_code = ?6, last_updated_by = ?7, last_updated_date = ?8 where id = ?9", nativeQuery = true)
	int updateOrphanDetails(@Param("address") String address, @Param("contact") long contact, @Param("fathers_name") String fathersName,
			@Param("mothers_name") String mothersName, @Param("name") String name, @Param("postal_code") long postalCode,
			@Param("last_updated_by") String lastUpdatedBy, @Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("id") Long id);
}
