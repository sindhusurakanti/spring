package org.brightlife.api.service.repository;

import java.util.List;

import org.brightlife.api.service.entity.StudentApplicationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentApplicationRepo extends CrudRepository<StudentApplicationEntity, Long> {

	@Query(value = "SELECT * FROM student_application WHERE student_id = ?1 order by created_date desc limit 1", nativeQuery = true)
	StudentApplicationEntity findApplicationID(@Param("student_id") long studentID);

	@Query(value = "SELECT * FROM student_application WHERE applied_by = ?1", nativeQuery = true)
	List<StudentApplicationEntity> findAllByUserId(@Param("user_id") long userId);

	/*@Query(value = "SELECT * FROM student_application WHERE applied_by = ?1 and status = ?2", nativeQuery = true)
	StudentApplicationEntity findByUserId(@Param("applied_by") long userId, @Param("status") String status);*/

	@Query(value = "SELECT * FROM student_application WHERE student_id = ?1", nativeQuery = true)
	List<StudentApplicationEntity> findAllById(@Param("student_id") long studentId);

	@Query(value = "SELECT * FROM student_application WHERE id = ?1", nativeQuery = true)
	StudentApplicationEntity findById(@Param("id") long id);

	@Query(value = "SELECT id FROM applicant_details WHERE student_id = ?1", nativeQuery = true)
	long findAppIdById(@Param("user_id") long userId);

	@Query(value = "SELECT id FROM applicant_details", nativeQuery = true)
	List<Long> findAppId();

	@Query(value = "SELECT * FROM student_application where status = ?1", nativeQuery = true)
	List<StudentApplicationEntity> findRecords(@Param("status") String status);

}
