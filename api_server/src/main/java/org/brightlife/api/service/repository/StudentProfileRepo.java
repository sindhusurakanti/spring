package org.brightlife.api.service.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.brightlife.api.service.entity.StudentProfileEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentProfileRepo extends CrudRepository<StudentProfileEntity, Long> {

	@Query(value = "SELECT * FROM student_profile WHERE user_id = ?1 and full_name = ?2 and created_date = ?3", nativeQuery = true)
	StudentProfileEntity findByDetails(@Param("user_id") long userId, @Param("full_name") String name, @Param("created_date") LocalDateTime ldt);

	@Query(value = "SELECT * FROM student_profile WHERE user_id = ?1 order by created_date desc limit 1", nativeQuery = true)
	StudentProfileEntity findStudentId(@Param("user_id") long userId);

	@Query(value = "SELECT * FROM student_profile WHERE id = ?1", nativeQuery = true)
	StudentProfileEntity findByStudentId(@Param("id") long studentId);

	@Query(value = "SELECT * FROM student_profile WHERE full_name like %?1% and city = ?2 and lives_with_or_in = ?3", nativeQuery = true)
	List<StudentProfileEntity> findByNameCityLives(@Param("name") String name, @Param("city") String city,
			@Param("lives_with_or_in") String livesWithOrIn);

	@Query(value = "SELECT * FROM student_profile WHERE full_name like %?1% and city = ?2", nativeQuery = true)
	List<StudentProfileEntity> findByNameCity(@Param("name") String name, @Param("city") String city);

	@Query(value = "SELECT * FROM student_profile WHERE full_name like %?1% and lives_with_or_in = ?2", nativeQuery = true)
	List<StudentProfileEntity> findByNameLivesWith(@Param("name") String name, @Param("lives_with_or_in") String livesWithOrIn);

	@Query(value = "SELECT * FROM student_profile WHERE city = ?1 and lives_with_or_in = ?2", nativeQuery = true)
	List<StudentProfileEntity> findByCityLivesWith(@Param("city") String city, @Param("lives_with_or_in") String livesWithOrIn);

	@Query(value = "SELECT * FROM student_profile WHERE lives_with_or_in = ?1", nativeQuery = true)
	List<StudentProfileEntity> findByLivesWith(@Param("lives_with_or_in") String livesWithOrIn);

	@Query(value = "SELECT * FROM student_profile WHERE full_name like %?1%", nativeQuery = true)
	List<StudentProfileEntity> findByName(@Param("name") String name);

	@Query(value = "SELECT * FROM student_profile WHERE city = ?1", nativeQuery = true)
	List<StudentProfileEntity> findByCity(@Param("city") String city);

	@Query(value = "SELECT * FROM student_profile WHERE full_name like  %?1% or city = ?2 or lives_with_or_in = ?3 or country = ?4  "
			+ "  or gender = ?5 or income = ?6 or state = ?7 or (year(date_of_birth) >= ?8 and year(date_of_birth) <= ?9)", nativeQuery = true)
	List<StudentProfileEntity> findStudentDetails(@Param("full_name") String name, @Param("city") String city,
			@Param("lives_with_or_in") String livesWithOrIn, @Param("country") String country, @Param("gender") String gender,
			@Param("income") String income, @Param("state") String state, @Param("date_of_birth") int upperRange,
			@Param("date_of_birth") int lowerRange);

	@Query(value = "SELECT * FROM student_profile WHERE full_name like  %?1% or city = ?2 or lives_with_or_in = ?3 or country = ?4  "
			+ "  or gender = ?5 or income = ?6 or state = ?7 ", nativeQuery = true)
	List<StudentProfileEntity> findStudentDetails(@Param("full_name") String name, @Param("city") String city,
			@Param("lives_with_or_in") String livesWithOrIn, @Param("country") String country, @Param("gender") String gender,
			@Param("income") String income, @Param("state") String state);

	@Query(value = "SELECT * FROM student_profile WHERE full_name like  %?1% or city = ?2 or lives_with_or_in = ?3 or country = ?4  "
			+ "  or gender = ?5 or income = ?6 or state = ?7 or month(date_of_birth) = ?8", nativeQuery = true)
	List<StudentProfileEntity> findStudentDetails(@Param("full_name") String name, @Param("city") String city,
			@Param("lives_with_or_in") String livesWithOrIn, @Param("country") String country, @Param("gender") String gender,
			@Param("income") String income, @Param("state") String state, @Param("date_of_birth") int birthMonth);

	@Query(value = "SELECT * FROM student_profile WHERE full_name like  %?1% or city = ?2 or lives_with_or_in = ?3 or country = ?4  "
			+ "  or gender = ?5 or income = ?6 or state = ?7 or (year(date_of_birth) <= ?8 and year(date_of_birth) >= ?9) or month(date_of_birth) = ?10", nativeQuery = true)
	List<StudentProfileEntity> findStudentDetails(@Param("full_name") String name, @Param("city") String city,
			@Param("lives_with_or_in") String livesWithOrIn, @Param("country") String country, @Param("gender") String gender,
			@Param("income") String income, @Param("state") String state, @Param("date_of_birth") int upperRange,
			@Param("date_of_birth") int lowerRange, @Param("date_of_birth") int birthMonth);

	@Query(value = "SELECT * FROM student_profile WHERE (full_name like  %?1% or city = ?2 or lives_with_or_in = ?3 or country = ?4  "
			+ "  or gender = ?5 or income = ?6 or state = ?7 or (year(date_of_birth) >= ?8 and year(date_of_birth) <= ?9) ) and is_orphan = true", nativeQuery = true)
	List<StudentProfileEntity> findStudentDetails(@Param("full_name") String name, @Param("city") String city,
			@Param("lives_with_or_in") String livesWithOrIn, @Param("country") String country, @Param("gender") String gender,
			@Param("income") String income, @Param("state") String state, @Param("date_of_birth") int upperRange,
			@Param("date_of_birth") int lowerRange, @Param("is_orphan") String orphan);

	@Query(value = "SELECT * FROM student_profile WHERE (full_name like  %?1% or city = ?2 or lives_with_or_in = ?3 or country = ?4  "
			+ "  or gender = ?5 or income = ?6 or state = ?7 ) and is_orphan = true", nativeQuery = true)
	List<StudentProfileEntity> findStudentDetails(@Param("full_name") String name, @Param("city") String city,
			@Param("lives_with_or_in") String livesWithOrIn, @Param("country") String country, @Param("gender") String gender,
			@Param("income") String income, @Param("state") String state, @Param("is_orphan") String orphan);

	@Query(value = "SELECT * FROM student_profile WHERE (full_name like  %?1% or city = ?2 or lives_with_or_in = ?3 or country = ?4  "
			+ "  or gender = ?5 or income = ?6 or state = ?7 or month(date_of_birth) = ?8 ) and is_orphan = true", nativeQuery = true)
	List<StudentProfileEntity> findStudentDetails(@Param("full_name") String name, @Param("city") String city,
			@Param("lives_with_or_in") String livesWithOrIn, @Param("country") String country, @Param("gender") String gender,
			@Param("income") String income, @Param("state") String state, @Param("date_of_birth") int birthMonth, @Param("is_orphan") String orphan);

	@Query(value = "SELECT * FROM student_profile WHERE (full_name like  %?1% or city = ?2 or lives_with_or_in = ?3 or country = ?4  "
			+ "  or gender = ?5 or income = ?6 or state = ?7 or (year(date_of_birth) <= ?8 and year(date_of_birth) >= ?9) or month(date_of_birth) = ?10 ) and is_orphan = true", nativeQuery = true)
	List<StudentProfileEntity> findStudentDetails(@Param("full_name") String name, @Param("city") String city,
			@Param("lives_with_or_in") String livesWithOrIn, @Param("country") String country, @Param("gender") String gender,
			@Param("income") String income, @Param("state") String state, @Param("date_of_birth") int upperRange,
			@Param("date_of_birth") int lowerRange, @Param("date_of_birth") int birthMonth, @Param("is_orphan") String orphan);

	@Query(value = "select id from student_profile where user_id = ?1", nativeQuery = true)
	List<Long> getStudentProfileIds(@Param("user_id") long userId);

	@Modifying
	@Query(value = "update student_profile set city = ?1, contact = ?2, country = ?3, date_of_birth = ?4, district = ?5,"
			+ "door_no = ?6, email = ?7, full_name = ?8, gender = ?9, income = ?10, language_spoken = ?11, last_updated_by = ?12,"
			+ "last_updated_date = ?13, lives_with_or_in = ?14, postal_code = ?15, state = ?16, street = ?17 where id = ?18", nativeQuery = true)
	int updateStudentRecord(@Param("city") String city, @Param("contact") String contact, @Param("country") String country,
			@Param("date_of_birth") Date dateOfBirth, @Param("district") String district, @Param("door_no") String doorNo,
			@Param("email") String email, @Param("full_name") String fullName, @Param("gender") String gender, @Param("income") Long income,
			@Param("language_spoken") String languageSpoken, @Param("last_updated_by") String lastUpdatedBy,
			@Param("last_updated_date") LocalDateTime lastUpdatedDate, @Param("lives_with_or_in") String livesWithOrIn,
			@Param("postal_code") int postalCode, @Param("state") String state, @Param("street") String street, @Param("id") long id);

}